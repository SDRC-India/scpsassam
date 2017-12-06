package org.sdrc.scpsassam.service;

import java.util.Map;

import org.json.simple.JSONArray;
import org.sdrc.scpsassam.model.NewIndicatorModel;

public interface IndicatorManagementService {
	
	
	public Map<String, JSONArray> initializeJson();
	
	
	public boolean saveNewIndicator(NewIndicatorModel newIndicatorModel);

}
