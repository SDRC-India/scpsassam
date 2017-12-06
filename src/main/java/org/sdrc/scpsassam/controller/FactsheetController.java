package org.sdrc.scpsassam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sdrc.scpsassam.domain.Agency;
import org.sdrc.scpsassam.repository.AgencyRepository;
import org.sdrc.scpsassam.service.FactsheetService;
import org.sdrc.scpsassam.util.CookieHandler;
import org.sdrc.scpsassam.util.FactsheetObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FactsheetController {

	@Autowired
	private FactsheetService factsheetService;

	@Autowired
	private AgencyRepository agencyRepository;
	
	@Autowired
	private CookieHandler cookieHandler;

	@RequestMapping("getPrefetchData")
	@ResponseBody
	public Object getPrefetchData( HttpServletResponse res, HttpServletRequest req) {

		Agency agency = agencyRepository.findByAgencyId(1);
		return factsheetService.getPrefetchData(agency.getAgencyId());
	}

	@RequestMapping(value = { "factSheetData" })
	@ResponseBody
	public Object FactSheetData(@RequestBody FactsheetObject factsheetObject, HttpServletResponse res, HttpServletRequest req) {
			Agency agency = agencyRepository.findByAgencyId(1);
		return factsheetService.getFactSheetData(factsheetObject, agency);
	}
}
