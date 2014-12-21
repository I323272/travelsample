package com.travel.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.travel.dto.HotelListData;
import com.travel.dto.Hotels;
import com.travel.service.HotelService;

@Component(value = "hotelService")
public class HotelServiceImpl implements HotelService {

    private static List<Hotels> hotelArray = null;
    private List<Hotels> filteredList = new ArrayList<Hotels>();
    private List<Hotels> cityList=null;
    private List<Hotels> priceList=null;
    private static final String jsonFilePath = "http://deals.expedia.com/beta/deals/hotels.json";

    static {
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(new URL(
                    jsonFilePath).openStream(), "UTF-8"));
            JsonArray userArray = new JsonParser().parse(reader)
                    .getAsJsonArray();
            if (userArray != null && userArray.isJsonArray()) {
                hotelArray = new ArrayList<Hotels>();
                for (JsonElement aUser : userArray) {
                    Hotels hotels = new Gson().fromJson(aUser, Hotels.class);
                    hotelArray.add(hotels);
                }
            }

        } catch (FileNotFoundException e) {
            hotelArray = null;
            e.printStackTrace();
        } catch (IOException e) {
            hotelArray = null;
            e.printStackTrace();
        }

    }

    @Override
    public HotelListData getAllHotels(int pageNo, int offset) {
        boolean moreResults = true;
        HotelListData hotelListData = new HotelListData();
        List<Hotels> paginatedHotelList = Collections.emptyList();
        int startLoc = pageNo * offset;
        if (filteredList != null && !filteredList.isEmpty()
                && startLoc <= filteredList.size() - 1) {
            paginatedHotelList = new ArrayList<Hotels>();
            int noelements = filteredList.size() - startLoc;
            if (noelements <= offset) {
                offset = noelements;
                moreResults = false;
            }
            for (int i = 0; i < offset; i++) {
                paginatedHotelList.add(filteredList.get(startLoc + i));
            }
        } else {
            moreResults = false;
        }
        hotelListData.setPaginatedHotelList(paginatedHotelList);
        hotelListData.setMoreResults(moreResults);

        return hotelListData;
    }

    @Override
    public HotelListData getFilteredData(boolean filter, Map<String,String> param, int pageNo, int offset) {
        if(pageNo==0) {
        if (!filter) {
            if(filteredList!=null) {
                filteredList.clear();
            }
            filteredList.addAll(hotelArray);
        }
        else {
               cityList=getCityFilteredList(param.get("city"));
               System.out.println(cityList.size());
               priceList=getPriceFilteredList(param.get("price"));
               System.out.println(priceList.size());
               cityList.retainAll(priceList);
               System.out.println(cityList.size());
               Set<Hotels> filteredSet = new HashSet<Hotels>(cityList);
               filteredList.clear();
               filteredList.addAll(filteredSet);
        }
        }
        HotelListData hotelListData = getAllHotels(pageNo, offset);
        return hotelListData;
    }
    
    private List<Hotels> getCityFilteredList(String data) {
        List<Hotels> list=new ArrayList<Hotels>();
        if(StringUtils.isEmpty(data)) {
            return hotelArray;
        }
        for (int i = 0; i < hotelArray.size(); i++) {
                if (StringUtils.containsIgnoreCase(hotelArray.get(i).getCity(), data)){
                    list.add(hotelArray.get(i));
                }
    }
        return list;
    }
    
    private List<Hotels> getPriceFilteredList(String data) {
        List<Hotels> list=new ArrayList<Hotels>();
        if(StringUtils.isEmpty(data)) {
            return hotelArray;
        }
        for (int i = 0; i < hotelArray.size(); i++) {
            if(hotelArray.get(i).getTotalRate()<=Float.parseFloat(data)) {
                list.add(hotelArray.get(i));
        }
    }
        return list;

}
}
