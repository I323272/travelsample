package com.travel.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travel.dto.HotelListData;
import com.travel.service.HotelService;

/**
 * This Controller provides the list of filtered data for all the travel deals
 */

@Controller
public class TravelDealController {

    private final static Logger LOGGER = Logger.getLogger(TravelDealController.class.getName()); 

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/travelDeal", method = RequestMethod.GET)
    public String displayTravelDeals() {

        return "/homePage";
    }

    /**
     * This method provides the list of travel deals page wise and also provide
     * a boolean flag where more pages are available or not
     */

    @ResponseBody
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public HotelListData getFilteredData(HttpServletRequest request) {
        LOGGER.info("Entering getFilteredData");
        HotelListData hotelsData=null;
        String formDataValues[] = request
                .getParameterValues("formDataValues[]");
        String formDataParams[] = request
                .getParameterValues("formDataParams[]");
        Map<String, String> paramMap = new HashMap<String, String>();
        for (int i = 0; i < formDataValues.length; i++) {
            paramMap.put(formDataParams[i], formDataValues[i]);
        }

        try {
        hotelsData = hotelService.getFilteredData(
                Boolean.parseBoolean(request.getParameter("filter")), paramMap,
                Integer.parseInt(request.getParameter("pageNo")),
                Integer.parseInt(request.getParameter("offset")));
        } catch(Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Filtered List size is " +hotelsData.getPaginatedHotelList().size());
        LOGGER.info("Exiting getFilteredData");
        return hotelsData;
    }

}
