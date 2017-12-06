package org.sdrc.scpsassam.controller;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sdrc.core.Authorize;
import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.domain.Facility;
import org.sdrc.scpsassam.domain.FacilityUserMapping;
import org.sdrc.scpsassam.domain.FaciltyDataDistrictAndTimeperiodWise;
import org.sdrc.scpsassam.domain.Role;
import org.sdrc.scpsassam.domain.User;
import org.sdrc.scpsassam.exceptions.DataEntryDateExceededException;
import org.sdrc.scpsassam.model.DataEntryModel;
import org.sdrc.scpsassam.model.UserModel;
import org.sdrc.scpsassam.service.AggregationService;
import org.sdrc.scpsassam.service.DataEntryService;
import org.sdrc.scpsassam.util.Constants;
import org.sdrc.scpsassam.util.StateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */
@Controller
public class DataEntryController {

	@Autowired
	private StateManager stateManager;

	@Autowired
	private DataEntryService dataEntryService;

	@Autowired
	private ResourceBundleMessageSource errorMessageSource;
	
	@Autowired
	private ResourceBundleMessageSource messages;

	@Autowired
	private AggregationService aggregationService;

	private final Logger _log = LoggerFactory.getLogger(DataEntryController.class);

	

	
	@Authorize(feature = "data_entry", permission = "edit")
	@RequestMapping("/dataEntryJsonData")
	@ResponseBody
	public JSONObject initalizeIndicator() {
		return dataEntryService.getIndicatorsJsonForDataEntry();
	}

	@Authorize(feature = "data_entry", permission = "edit")
	@RequestMapping("/saveDataEntryJsonData")
	@ResponseBody
	public Map<String, String> postDataEntryJsonData(@RequestBody List<DataEntryModel> dataEntryJson, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			boolean isSaved = dataEntryService.saveIndicatorsJsonFromDataEntry(dataEntryJson);
			map.put("status", "200"); 
			map.put("message", "Data submission was successful!");

		} catch (DataEntryDateExceededException ex) {

			_log.error("{}", ex);
			map.put("status", "400");
			map.put("message", errorMessageSource.getMessage(Constants.Web.DATA_ENTRY_DATE_EXCEEDED, null, null));

		} catch (Exception ex) {
			_log.error("{}", ex);
			map.put("status", "400");
			map.put("message", errorMessageSource.getMessage(Constants.Web.INTERNAL_ERROR, null, null));
		}
		return map;
	}

	@Authorize(feature = "data_entry", permission = "view")
	@RequestMapping("/getSubmissionHistory")
	@ResponseBody
	public JSONObject getSubmissionHistory() {
		try{
		return dataEntryService.returnSubmissionManagementDetails();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Authorize(feature = "data_entry", permission = "edit")
	@RequestMapping("/dataEntry")
	public String getDataEntryPage() {
		return "dataEntry";
	}
	

	@Authorize(feature = "data_entry", permission = "edit")
	@RequestMapping("/getPreviewData")
	@ResponseBody
	public JSONObject getPreviewData(@RequestParam("facilityId") Integer facilityId,@RequestParam("month") Integer month,@RequestParam("year") Integer year) {
		return dataEntryService.getPreviewData(facilityId,month,year);
	}
	

	
}
