package com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.dto.Hotels;
import com.travel.service.HotelService;


@Controller
public class TravelDealController {
    
    @Autowired
    private HotelService hotelService;
    
    @RequestMapping("/travelDeal")
    public String displayTravelDeals(final Model model){

        return "../homePage";
    }
    
    @RequestMapping("/hotelsList") 
     public List<Hotels> getHotelsList(int startIndex,int offset)
            {
                List<Hotels> hotelsList=hotelService.getAllHotels(startIndex,offset);
                System.out.println("hello");
                return hotelsList;
            }


}
