package com.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.travel.dto.Hotels;


@Controller
public class TravelDealController {
    
    @RequestMapping("/travelDeal")
    public String displayTravelDeals(final Model model){

        return "../homePage";
    }
    
    @RequestMapping("/hotelsList") 
     public Hotels getHotelsList(String param)
                 {
                return null;
            }


}
