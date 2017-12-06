package org.sdrc.scpsassam.controller;

import java.util.Map;

import org.json.simple.JSONArray;
import org.sdrc.core.Authorize;
import org.sdrc.scpsassam.model.NewIndicatorModel;
import org.sdrc.scpsassam.service.IndicatorManagementService;
import org.sdrc.scpsassam.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */
@Controller
public class IndicatorManagementController {

	@Autowired
	private IndicatorManagementService indicatorManagementService;

	@Autowired
	private ResourceBundleMessageSource errorMessageSource;

	@Autowired
	private ResourceBundleMessageSource messages;

	@Authorize(feature = "indicator_mgmt", permission = "edit")
	@RequestMapping(value = "/initIndicatorManagementView", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, JSONArray> getInitalJson() {
		return indicatorManagementService.initializeJson();
	}

	@Authorize(feature = "indicator_mgmt", permission = "edit")
	@RequestMapping(value = "/addNewIndicator", method = RequestMethod.POST)
	public ResponseEntity<String> submitNewIndicator(@RequestBody NewIndicatorModel newIndicatorModel) {

		try {
			boolean isIndicatorSaved = indicatorManagementService.saveNewIndicator(newIndicatorModel);
			if (isIndicatorSaved) {
				return new ResponseEntity<String>(messages.getMessage(Constants.Web.INDICATOR_SAVED_SUCCESSFULLY, null, null), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(errorMessageSource.getMessage(Constants.Web.INTERNAL_ERROR, null, null), HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>(errorMessageSource.getMessage(Constants.Web.INTERNAL_ERROR, null, null), HttpStatus.BAD_REQUEST);
		}

	}
	
	@Authorize(feature = "indicator_mgmt", permission = "edit")
	@RequestMapping("/indicatorManagement")
	public String getDataEntryPage() {
		return "indicatorManagement";
	}

}
