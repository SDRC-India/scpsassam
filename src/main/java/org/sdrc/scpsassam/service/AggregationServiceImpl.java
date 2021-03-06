
package org.sdrc.scpsassam.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.sdrc.core.IndicatorClassificationType;
import org.sdrc.core.IndicatorType;
import org.sdrc.core.Type;
import org.sdrc.core.UnitType;
import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.domain.Area;
import org.sdrc.scpsassam.domain.Data;
import org.sdrc.scpsassam.domain.Indicator;
import org.sdrc.scpsassam.domain.IndicatorClassification;
import org.sdrc.scpsassam.domain.IndicatorUnitSubgroup;
import org.sdrc.scpsassam.domain.Subgroup;
import org.sdrc.scpsassam.domain.Timeperiod;
import org.sdrc.scpsassam.domain.Unit;
import org.sdrc.scpsassam.repository.AgencyRepository;
import org.sdrc.scpsassam.repository.DataEntryRepository;
import org.sdrc.scpsassam.repository.FaciltyDataDistrictAndTimeperiodWiseRepository;
import org.sdrc.scpsassam.repository.IndicatorClassificationRepository;
import org.sdrc.scpsassam.repository.IndicatorRepository;
import org.sdrc.scpsassam.repository.IndicatorUnitSubgroupRepository;
import org.sdrc.scpsassam.repository.TimePeriodRepository;
import org.sdrc.scpsassam.repository.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
/**
 * 
 * @author Azaruddin(azaruddin@sdrc.co.in)
 *
 */

@Service
public class AggregationServiceImpl implements AggregationService {

	@Autowired
	private AgencyRepository agencyRepository;
	@Autowired
	private IndicatorRepository indicatorRepository;
	@Autowired
	private FaciltyDataDistrictAndTimeperiodWiseRepository faciltyDataDistrictAndTimeperiodWiseRepository;
	@Autowired
	private TimePeriodRepository timePeriodRepository;
	@Autowired
	private DataEntryRepository dataEntryRepository;
	@Autowired
	private IndicatorClassificationRepository indicatorClassificationRepository;
	@Autowired
	private IndicatorUnitSubgroupRepository indicatorUnitSubgroupRepository;
	@Autowired
	private UnitRepository unitRepository;


	private final Logger _log = LoggerFactory.getLogger(AggregationServiceImpl.class);


	/**
	 * Starts aggregated for data entered in last month.This method is executed by cron job every day.and its checks for
	 * an agency the aggregation day specified for agency
	 */

	@Transactional
	@Override
	public boolean startAggregation() {
		List<Agency> agencies = agencyRepository.findAll();

		for (Agency agency : agencies) {

			int day = 0;
			int year = 0;
			int month = 0;

			if (agency.getLastAggregationDate() == null) {
				LocalDateTime now = LocalDateTime.now();
				day = now.getDayOfMonth();
				year = now.getYear();
				month = now.getMonthValue();

				if (month == 1) {
					month = 12;
					year = year - 1;
				} else {
					month = month - 1;
				}

				if (agency.getAggStartDay() == day) {
					aggregateDataByAgency(agency, year, month);
					createIndex(agency, year, month);
					agency.setLastAggregationDate(new Date());
				}

			} else {
				// pickup last aggregate date, and iterate aggregation for current month
				Date lastAggregated = agency.getLastAggregationDate();

				LocalDate lastAggregatedDate = new LocalDate(lastAggregated.getTime());
				int lastAggMonth = lastAggregatedDate.getMonthOfYear();
				int lastAggYear = lastAggregatedDate.getYear();

				LocalDateTime now = LocalDateTime.now();
				day = now.getDayOfMonth();

				int currentYear = now.getYear();
				int currentMonth = now.getMonthValue();

				Date day1OfLastAggDate = null;
				Date day1OfCurrDate = null;
				try {
					day1OfLastAggDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastAggYear + "-" + (lastAggMonth < 10 ? "0" + lastAggMonth : lastAggMonth) + "-01 00:00:00");
					day1OfCurrDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentYear + "-" + (currentMonth < 10 ? "0" + currentMonth : currentMonth) + "-01 00:00:00");
				} catch (ParseException e) {
					throw new RuntimeException("Unable to parse date :",e);
				}

				DateTime sDate = new DateTime(day1OfLastAggDate.getTime());
				DateTime eDate = new DateTime(day1OfCurrDate.getTime());
				int difInMonths = Months.monthsBetween(sDate, eDate).getMonths();

				_log.debug("Last agg Date :{}" , day1OfLastAggDate);
				_log.debug("Curr Date :{}" , day1OfCurrDate);
				_log.debug("Diff In Months :{}" , Months.monthsBetween(sDate, eDate).getMonths());

				for (int monthToIterate = 0; monthToIterate < difInMonths; monthToIterate++) {

					agency = agencyRepository.findByAgencyId(1);

					lastAggregated = agency.getLastAggregationDate();

					LocalDate lastA = new LocalDate(lastAggregated.getTime());

					lastAggMonth = lastA.getMonthOfYear();
					lastAggYear = lastA.getYear();

					_log.debug("Last agg Month :{}" , lastAggMonth);
					_log.debug("Last agg Year :{}" , lastAggYear);


					if (agency.getAggStartDay() == day) {
						aggregateDataByAgency(agency, lastAggYear, lastAggMonth);
						createIndex(agency, lastAggYear, lastAggMonth);
						agency.setLastAggregationDate(new Date());
					}

				}

			}

		}
		return true;
	}

	@Transactional
	public boolean aggregateDataByAgency(Agency agency, int year, int month) {

		String monthString = "";
		if (month >= 10) {
			monthString = month + "";
		} else {
			monthString = "0" + month;
		}
		Year y = Year.of(year);
		Month m = Month.of(month);

		String dayString = "";
		if (m.length(y.isLeap()) < 10) {
			dayString = "0" + m.length(y.isLeap());
		} else {
			dayString = m.length(y.isLeap()) + "";
		}

		Timeperiod timeperiod = timePeriodRepository.findByTimePeriod(year + "." + monthString);
		if (timeperiod == null) {
			Timeperiod newTimeperiod = new Timeperiod();
			newTimeperiod.setTimePeriod(year + "." + monthString);
			try {
				newTimeperiod.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + monthString + "-01"));
				newTimeperiod.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + monthString + "-" + dayString));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			newTimeperiod.setPeriodicity("1");
			timeperiod = timePeriodRepository.save(newTimeperiod);
		}

		List<Object[]> objArr = faciltyDataDistrictAndTimeperiodWiseRepository.aggregateRawDataByIndicatorAndAgencyAndMonthAndYearAndRole(month, year, agency);

		// Note taking data was entered in Unit "as Percent" and Subgroup as
		// "total" for all districts of an agency
		List<Data> datas = new ArrayList<Data>();
		for (Object[] obj : objArr) {
			int areaId = Integer.parseInt((obj[0].toString()));
			int indicatorId = Integer.parseInt((obj[1].toString()));
			int unitId = Integer.parseInt((obj[2].toString()));
			int subgroupId = Integer.parseInt((obj[3].toString()));
			int numerator = Integer.parseInt((obj[4].toString()));
			int denominator = Integer.parseInt((obj[5].toString()));

			Area district = new Area(areaId);

			Indicator indicator = indicatorRepository.findByIndicatorId(indicatorId);

			Unit unit = new Unit(unitId);

			Subgroup subgroup = new Subgroup(subgroupId);

			IndicatorUnitSubgroup ius = indicatorUnitSubgroupRepository.findByIndicatorAndUnitAndSubgroup(indicator, unit, subgroup);

			IndicatorClassification source = indicator.getIndicatorRoleMapping().getRole().getRoleSourceMapping().getIc();

			Data data = dataEntryRepository.findByIndicatorUnitSubgroupAndSourceAndTimePeriodAndArea(ius, source, timeperiod, district);

			if (data == null) {
				data = new Data();
				data.setArea(district);
				data.setIndicator(indicator);
				data.setIndicatorUnitSubgroup(ius);
				data.setSubgroup(subgroup);
				data.setTimePeriod(timeperiod);
				data.setUnit(unit);
				data.setSource(source);
				data.setPublished(true);
			}

			data.setDenominator(denominator);
			data.setNumerator(numerator);
			
			if (denominator == 0) {
				data.setPercentage(0.00d);
			} else {
				int num = numerator * 100;
				data.setPercentage((new BigDecimal(num).divide(new BigDecimal(denominator), 1, RoundingMode.HALF_UP)).doubleValue());
			}
			datas.add(data);

		}

		datas = dataEntryRepository.save(datas);
		return true;
	}

	@Transactional
	public boolean createIndex(Agency agency, int year, int month) {
		DecimalFormat df = new DecimalFormat("#.##");
		String monthString = "";
		if (month >= 10) {
			monthString = month + "";
		} else {
			monthString = "0" + month;
		}
		Timeperiod timeperiod = timePeriodRepository.findByTimePeriod(year + "." + monthString);
		if (timeperiod == null) {
			Timeperiod newTimeperiod = new Timeperiod();
			newTimeperiod.setTimePeriod(year + "." + monthString);
			timeperiod = timePeriodRepository.save(newTimeperiod);
		}

		Unit indexUnit = unitRepository.findByUnitType(UnitType.INDEX);

		IndicatorClassification sourceForComputedIndex = indicatorClassificationRepository.findByType(Type.INDEX_COMPUTED);

		Map<Integer, List<Data>> indexAgainstSector = new LinkedHashMap<>();

		Unit percentageUnit = unitRepository.findByUnitType(UnitType.PERCENTAGE);

		Subgroup subgroup = new Subgroup();
		subgroup.setSubgroupValueId(1);

		List<IndicatorClassification> ics = indicatorClassificationRepository.findByIndicatorClassificationTypeAndParentIsNullAndIndexIsFalse(IndicatorClassificationType.SC);

		for (IndicatorClassification indicatorClassification : ics) {

			_log.debug("----------------------------------Normalized Values-------------------------------");
			List<Data> indexDataForAllDistricts = new ArrayList<>();

			List<Integer> subsectorIds = new ArrayList<>();

			for (IndicatorClassification subsector : indicatorClassification.getChildren()) {
				subsectorIds.add(subsector.getIndicatorClassificationId());
			}

			// getting indicators for the agency
			List<Indicator> indicators = indicatorRepository.findIndicatorsOfAgencyBySector(agency, subsectorIds);

			Map<Integer, List<Data>> districtWiseAllIndicatorsNormalizedDataInList = new LinkedHashMap<>();

			for (Indicator indicator : indicators) {

				// for each indicator find values in all districts
				// and find
				// max and min , then normalize those value
				// since indicator is agency specific we dont have
				// to worry.

				// find data time period against agency id

				List<Data> dataValues = dataEntryRepository.findByIndicatorAndUnitAndSubgroupAndSourceAndTimePeriodOrderByPercentageAsc(indicator, percentageUnit, subgroup, indicator.getIndicatorRoleMapping().getRole().getRoleSourceMapping().getIc(), timeperiod);
				System.out.println("Source:"+indicator.getIndicatorRoleMapping().getRole().getRoleSourceMapping().getIc().getName());
				System.out.println("Source:"+indicator.getIndicatorRoleMapping().getRole().getRoleSourceMapping().getIc().getIndicatorClassificationId());
				_log.info("indicator Names : {} \nDataValues List DistrictWise : {}", indicator.getIndicatorName(), dataValues);
				System.out.println("Data value size for indicator:::"+dataValues.size());
				_log.info("indicator Id : {}", indicator.getIndicatorId());
				_log.info("unit Id : {}", percentageUnit.getUnitId());
				_log.info("subgroup Id : {}", subgroup.getSubgroupValueId());
				_log.info("source Id : {}", indicator.getIndicatorRoleMapping().getRole().getRoleSourceMapping().getIc().getIndicatorClassificationId());
				_log.info("timeperiod Id : {}", timeperiod.getTimeperiodId());

				if (dataValues != null && dataValues.size() != 0) {
					
					boolean doAllAreasHaveSameValue = dataValues.stream().allMatch(e -> e.getPercentage().compareTo(dataValues.get(0).getPercentage())==0);
					boolean doAllAreasHaveHundredForIndicator = dataValues.stream().allMatch(e -> e.getPercentage().compareTo(new Double("100.00"))==0);	
					boolean allAreasHavingZero = dataValues.stream().allMatch( i -> i.getPercentage().compareTo(new Double("0.00"))==0);	
					
					System.out.println("doAllAreasHaveSameValue :"+doAllAreasHaveSameValue);
					System.out.println("doAllAreasHaveHundredForIndicator :"+doAllAreasHaveHundredForIndicator);
					System.out.println("allAreasHavingZero :"+allAreasHavingZero);
					
					if(indicator.isHighIsGood()) {
						if(doAllAreasHaveHundredForIndicator) {
							for (Data data2 : dataValues) {
								data2.setNormalizedValue(new BigDecimal("1.00"));
								if (districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId()) == null) {
									List<Data> indicatorData = new ArrayList<>();
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								} else {
									List<Data> indicatorData = districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId());
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								}
							}
						}else if(doAllAreasHaveSameValue) {
							for (Data data2 : dataValues) {
								data2.setNormalizedValue(new BigDecimal("0.00"));
								if (districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId()) == null) {
									List<Data> indicatorData = new ArrayList<>();
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								} else {
									List<Data> indicatorData = districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId());
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								}
							}
						}else {
							BigDecimal minimum;
							BigDecimal maximum;
							if (dataValues.size() == 1) {
								minimum = new BigDecimal(df.format(dataValues.get(0).getPercentage()));
								maximum = new BigDecimal(df.format(dataValues.get(0).getPercentage()));
							} else {
								_log.debug("DF formatted minimum :{}", df.format(dataValues.get(0).getPercentage()));
								_log.debug("DF formatted maximum :{}", df.format(dataValues.get(dataValues.size() - 1).getPercentage()));

								minimum = dataValues.size() > 0 ? new BigDecimal(df.format(dataValues.get(0).getPercentage())).setScale(2) : new BigDecimal("0.00");
								maximum = dataValues.size() > 0 ? new BigDecimal(df.format(dataValues.get(dataValues.size() - 1).getPercentage())).setScale(2) : new BigDecimal("0.00");
							}
							for (Data data2 : dataValues) {
								data2.normalizeValue(maximum, minimum, indicator.isHighIsGood());
								if (districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId()) == null) {
									List<Data> indicatorData = new ArrayList<>();
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								} else {
									List<Data> indicatorData = districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId());
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								}
							}
						}
						
					}else {
						if(allAreasHavingZero) {
							for (Data data2 : dataValues) {
								data2.setNormalizedValue(new BigDecimal("1.00"));
								if (districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId()) == null) {
									List<Data> indicatorData = new ArrayList<>();
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								} else {
									List<Data> indicatorData = districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId());
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								}
							}
						}else if(doAllAreasHaveSameValue) {
							for (Data data2 : dataValues) {
								data2.setNormalizedValue(new BigDecimal("0.00"));
								if (districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId()) == null) {
									List<Data> indicatorData = new ArrayList<>();
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								} else {
									List<Data> indicatorData = districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId());
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								}
							}
						}else {
							BigDecimal minimum;
							BigDecimal maximum;
							if (dataValues.size() == 1) {
								minimum = new BigDecimal(df.format(dataValues.get(0).getPercentage()));
								maximum = new BigDecimal(df.format(dataValues.get(0).getPercentage()));
							} else {
								_log.debug("DF formatted minimum :{}", df.format(dataValues.get(0).getPercentage()));
								_log.debug("DF formatted maximum :{}", df.format(dataValues.get(dataValues.size() - 1).getPercentage()));

								minimum = dataValues.size() > 0 ? new BigDecimal(df.format(dataValues.get(0).getPercentage())).setScale(2) : new BigDecimal("0.00");
								maximum = dataValues.size() > 0 ? new BigDecimal(df.format(dataValues.get(dataValues.size() - 1).getPercentage())).setScale(2) : new BigDecimal("0.00");
							}
							for (Data data2 : dataValues) {
								data2.normalizeValue(maximum, minimum, indicator.isHighIsGood());
								if (districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId()) == null) {
									List<Data> indicatorData = new ArrayList<>();
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								} else {
									List<Data> indicatorData = districtWiseAllIndicatorsNormalizedDataInList.get(data2.getArea().getAreaId());
									indicatorData.add(data2);
									districtWiseAllIndicatorsNormalizedDataInList.put(data2.getArea().getAreaId(), indicatorData);
								}
							}
						}
					}
				}
			}

			Timeperiod tp = timeperiod;
			districtWiseAllIndicatorsNormalizedDataInList.forEach((district, normalizedDataOfAllIndicators) -> {

				BigDecimal avg = new BigDecimal(0.00);
				BigDecimal sum = new BigDecimal(0.00);

				for (Data data : normalizedDataOfAllIndicators) {
					sum = sum.add(data.getNormalizedValue());
					_log.debug("indicator Id :{}", data.getIndicator().getIndicatorId());
					_log.debug("Data Id :{}", data.getDataId());
					_log.debug("Normalized Value :{}", data.getNormalizedValue());
				}

				avg = sum.divide(new BigDecimal(normalizedDataOfAllIndicators.size()), 2, RoundingMode.HALF_UP);

				_log.debug("--Sum--:{}", sum);
				_log.debug("--Size--:{}", normalizedDataOfAllIndicators.size());
				_log.debug("--avg--:{}", avg);

				Area area = new Area();
				area.setAreaId(district);

				IndicatorClassification subsectorOfIndex = indicatorClassificationRepository.findBySectorIds(String.valueOf(indicatorClassification.getIndicatorClassificationId()));

				Indicator i = (indicatorRepository.findByAgencyAndIndicatorClassificationAndIndicatorType(agency, subsectorOfIndex, IndicatorType.INDEX_INDICATOR));

				IndicatorUnitSubgroup ius = indicatorUnitSubgroupRepository.findByIndicatorAndUnitAndSubgroup(i, indexUnit, subgroup);

				Data avgIndexOfDistrict = dataEntryRepository.findByIndicatorUnitSubgroupAndSourceAndTimePeriodAndArea(ius, sourceForComputedIndex, tp, area);

				if (avgIndexOfDistrict == null) {
					avgIndexOfDistrict  = new Data();
					avgIndexOfDistrict.setArea(area);
					avgIndexOfDistrict.setDenominator(0);
					avgIndexOfDistrict.setNumerator(0);
					avgIndexOfDistrict.setSource(sourceForComputedIndex);
					avgIndexOfDistrict.setSubgroup(subgroup);
					avgIndexOfDistrict.setTimePeriod(tp);
					avgIndexOfDistrict.setUnit(indexUnit);
					avgIndexOfDistrict.setIndicator(i);
					avgIndexOfDistrict.setIndicatorUnitSubgroup(ius);
					avgIndexOfDistrict.setPublished(true);

				}

				avgIndexOfDistrict.setPercentage(Double.valueOf(df.format(avg)));

				indexDataForAllDistricts.add(avgIndexOfDistrict);

			});
			
			indexAgainstSector.put(indicatorClassification.getIndicatorClassificationId(), indexDataForAllDistricts);

		}

		// Generating value for Overall Index

		Timeperiod tp = timeperiod;

		Map<Integer, List<Data>> districtOverallMap = new LinkedHashMap<>();

		indexAgainstSector.forEach((indicatorClassification, indexValues) -> {

			for (Data data2 : indexValues) {
				_log.debug("---------------------------------------------------------------------");
				_log.debug("indicator id:::::::{}", data2.getIndicator().getIndicatorId());
				_log.debug("ius id :::::::::{}", data2.getIndicatorUnitSubgroup().getIndicatorUnitSubgroupId());
				_log.debug("source id:::::::{}", data2.getSource().getIndicatorClassificationId());
				_log.debug("timeperiod id:::::::{}", data2.getTimePeriod().getTimePeriod());
				_log.debug("area id:::::::{}", data2.getArea().getAreaId());
				// checking data if exists for
				Data d = dataEntryRepository.findByIndicatorUnitSubgroupAndSourceAndTimePeriodAndArea(data2.getIndicatorUnitSubgroup(), data2.getSource(), data2.getTimePeriod(), data2.getArea());

				if (districtOverallMap.get(data2.getArea().getAreaId()) == null) {
					List<Data> indicatorData = new ArrayList<>();
					if (d == null)
						indicatorData.add(data2);
					else
						indicatorData.add(d);
					districtOverallMap.put(data2.getArea().getAreaId(), indicatorData);
				} else {
					List<Data> indicatorData = districtOverallMap.get(data2.getArea().getAreaId());
					if (d == null)
						indicatorData.add(data2);
					else
						indicatorData.add(d);
					districtOverallMap.put(data2.getArea().getAreaId(), indicatorData);
				}
			}

			// for each sector persist index values of overall
			dataEntryRepository.save(indexValues);
		});

		districtOverallMap.forEach((district, calculatedIndexForIndicators) -> {

			_log.debug("District :::{}", district);

			BigDecimal avg = new BigDecimal(0.00);
			BigDecimal sum = new BigDecimal(0.00);
			for (Data d : calculatedIndexForIndicators) {
				sum = sum.add(new BigDecimal(d.getPercentage()));

				_log.debug("Percentage:::::{}", d.getPercentage());
				_log.debug("Sum:::::{}", sum);
			}

			avg = sum.divide(new BigDecimal(calculatedIndexForIndicators.size()), 2, RoundingMode.HALF_UP);

			Indicator overallIndicator = indicatorRepository.findAllByAgencyAndIndicatorType(agency, IndicatorType.OVERALL).get(0);

			IndicatorUnitSubgroup ius = indicatorUnitSubgroupRepository.findByIndicatorAndUnitAndSubgroup(overallIndicator, indexUnit, subgroup);

			Area area = new Area();
			area.setAreaId(district);

			Data overallIndexOfDistrict = dataEntryRepository.findByIndicatorUnitSubgroupAndSourceAndTimePeriodAndArea(ius, sourceForComputedIndex, tp, area);

			if (overallIndexOfDistrict == null) {
				overallIndexOfDistrict = new Data();
				overallIndexOfDistrict.setArea(area);
				overallIndexOfDistrict.setDenominator(0);
				overallIndexOfDistrict.setNumerator(0);
				overallIndexOfDistrict.setSource(sourceForComputedIndex);
				overallIndexOfDistrict.setTimePeriod(tp);
				overallIndexOfDistrict.setUnit(indexUnit);
				overallIndexOfDistrict.setSubgroup(subgroup);
				overallIndexOfDistrict.setIndicator(overallIndicator);
				overallIndexOfDistrict.setIndicatorUnitSubgroup(ius);
				overallIndexOfDistrict.setPublished(true);
			}
			overallIndexOfDistrict.setPercentage(Double.valueOf(df.format(avg)));

			dataEntryRepository.save(overallIndexOfDistrict);

		});

		return true;
	}

	

}