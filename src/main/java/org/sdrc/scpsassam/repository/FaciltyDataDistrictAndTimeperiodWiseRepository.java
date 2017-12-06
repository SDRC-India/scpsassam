package org.sdrc.scpsassam.repository;

import java.util.List;

import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.domain.Facility;
import org.sdrc.scpsassam.domain.FaciltyDataDistrictAndTimeperiodWise;
import org.sdrc.scpsassam.domain.Indicator;
import org.springframework.data.repository.query.Param;

public interface FaciltyDataDistrictAndTimeperiodWiseRepository {

	public List<FaciltyDataDistrictAndTimeperiodWise> findByFacilityAndMonthAndYear(Facility facility, int month, int year);

	public FaciltyDataDistrictAndTimeperiodWise findByIndicatorAndFacilityAndMonthAndYear(Indicator indicator, Facility facility, int month, int year);
	
	public FaciltyDataDistrictAndTimeperiodWise save(FaciltyDataDistrictAndTimeperiodWise faciltyDataDistrictAndTimeperiodWise);

	public List<Object[]> aggregateRawDataByIndicatorAndAgencyAndMonthAndYearAndRole(int month, int year, Agency agency);
	
	
	public List<FaciltyDataDistrictAndTimeperiodWise> findByFacilityAndMonthGreaterThanEqualAndYearGreaterThanEqual(Facility facility, int month, int year);

}
