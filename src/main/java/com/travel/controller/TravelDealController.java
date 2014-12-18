package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travel.dto.HotelListData;
import com.travel.service.HotelService;


@Controller
public class TravelDealController {
    
    @Autowired
    private HotelService hotelService;
    
    @RequestMapping(value="/travelDeal", method = RequestMethod.GET)
    public String displayTravelDeals(){

        return "/homePage";
    }
    
    @ResponseBody
    @RequestMapping(value="/hotelsList", method = RequestMethod.POST) 
     public HotelListData getHotelsList(@RequestParam(value="pageNo",defaultValue="0")int pageNo,@RequestParam(value="offset",defaultValue="8")int offset) {
        HotelListData hotelsData=hotelService.getAllHotels(pageNo,offset);
                return hotelsData;
            }


}
