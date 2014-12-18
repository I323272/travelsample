package com.travel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travel.dto.Hotels;
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
     public List<Hotels> getHotelsList(HttpServletRequest request) {
        System.out.println(request.getParameter("pageNo"));
                int pageNo=Integer.parseInt(request.getParameter("pageNo"));
                int offset=Integer.parseInt(request.getParameter("offset"));
                List<Hotels> hotelsList=hotelService.getAllHotels(pageNo,offset);
                return hotelsList;
            }


}
