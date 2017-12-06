package org.sdrc.scpsassam.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.model.DataCollectionModel;
import org.sdrc.scpsassam.model.DataModel;
import org.sdrc.scpsassam.model.LineSeries;
import org.sdrc.scpsassam.model.ValueObject;

public interface DashboardService {

	List<ValueObject> fetchIndicators(String param);

	List<ValueObject> fetchSources(String param);

	List<ValueObject> fetchUtTimeperiod(Integer iusNid, Integer SourceNid);

	JSONArray fetchAllSectors();

	DataCollectionModel fetchData(String indicatorId, String sourceId, String parentAreaCode, String timeperiodId, Integer childLevel) throws ParseException;

	List<List<LineSeries>> fetchChartData(Integer iusNid, Integer areaNid) throws ParseException;


	List<DataModel> fetchPdfData(String indicatorId, String sourceId, String areaId, String timePeriodId, Integer childLevel);

	String exportPDF(List<String> svgs, String indicatorId, String sourceId, String parentAreaCode, String timeperiodId, Integer childLevel);

	String exportPDFLine(List<String> svgs, Integer iusNid, Integer areaNid);

	public boolean publishData();

	boolean displayPublishButton();

	boolean publishData(int agencyId, int year, int month, String monthString);
}
