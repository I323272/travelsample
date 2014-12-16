package com.travel.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.travel.dto.Hotels;
import com.travel.service.HotelService;

@Component(value="hotelService")
public class HotelServiceImpl implements HotelService{
    
    private static List<Hotels> hotelArray=new ArrayList<Hotels>();
    private static final String jsonFilePath = "http://deals.expedia.com/beta/deals/hotels.json";

    static {
        try {
        JsonReader reader = new JsonReader(
          new InputStreamReader(new URL(jsonFilePath).openStream(),"UTF-8"));
        JsonArray userArray =  new JsonParser().parse(reader).getAsJsonArray();
        for ( JsonElement aUser : userArray ){
                      Hotels hotels = new Gson().fromJson(aUser, Hotels.class);
                      hotelArray.add(hotels);
                    }
           
                }catch(FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }

    }
    
    @Override
    public List<Hotels> getAllHotels(int startIndex,int offset) {
        List<Hotels> paginatedHotelList=new ArrayList<Hotels>();
        
        for(int i=0;i<offset;i++) {
            paginatedHotelList.add(hotelArray.get(((startIndex-1)*offset)+i));
        }
        
        return paginatedHotelList;
    }

}
