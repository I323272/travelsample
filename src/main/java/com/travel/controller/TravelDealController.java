package com.travel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travel.dto.HotelListData;
import com.travel.service.HotelService;

@Controller
public class TravelDealController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/travelDeal", method = RequestMethod.GET)
    public String displayTravelDeals() {

        return "/homePage";
    }

    @ResponseBody
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public HotelListData getFilteredData(HttpServletRequest request) {
        String formDataValues[]=request.getParameterValues("formDataValues[]");
        String formDataParams[]=request.getParameterValues("formDataParams[]");;
        Map<String,String>paramMap=new HashMap<String,String>();
        for(int i=0;i<formDataValues.length;i++) {
            paramMap.put(formDataParams[i], formDataValues[i]);
        }
        
        HotelListData hotelsData = hotelService.getFilteredData(
                Boolean.parseBoolean(request.getParameter("filter")), paramMap,
                Integer.parseInt(request.getParameter("pageNo")), Integer.parseInt(request.getParameter("offset")));
        return hotelsData;
    }

}
