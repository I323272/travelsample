package com.travel.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
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
    private List<Hotels> list1=new ArrayList<Hotels>();
    private List<Hotels> list2=new ArrayList<Hotels>();
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
            hotelArray = Collections.emptyList();
            e.printStackTrace();
        } catch (IOException e) {
            hotelArray = Collections.emptyList();
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
                filteredList.clear();
            filteredList.addAll(hotelArray);
        }
        else {
               list1.clear();
               list2.clear();
               list1.addAll(getCityFilteredList(param.get("city")));
               list2.addAll(getPriceFilteredList(param.get("price")));
               list1.retainAll(list2);
               list2.clear();
               list2=getRangeFilteredList(param.get("ratingmin"), param.get("ratingmax"),"guestRating");
               list1.retainAll(list2);
               list2.clear();
               list2.addAll(getRangeFilteredList(param.get("latitudemin"), param.get("latitudemax"),"latitude"));
               list1.retainAll(list2);
               list2.clear();
               list2.addAll(getRangeFilteredList(param.get("longitudemin"), param.get("longitudemax"),"longitude"));
               list1.retainAll(list2);
               Set<Hotels> filteredSet = new HashSet<Hotels>(list1);
               if(filteredList!=null) {
               filteredList.clear();
               }
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
    
    private List<Hotels> getRangeFilteredList(String min,String max,String param) {
        List<Hotels> list=new ArrayList<Hotels>();
        if(StringUtils.isEmpty(min) && StringUtils.isEmpty(max)) {
            return hotelArray;
        }
        for (int i = 0; i < hotelArray.size(); i++) {
            try {
                if((Float)PropertyUtils.getProperty(hotelArray.get(i), param) >= Float.parseFloat(min) && (Float)PropertyUtils.getProperty(hotelArray.get(i), param) <= Float.parseFloat(max)) {
                    list.add(hotelArray.get(i));
                }
            } catch (NumberFormatException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            } 
    }
        return list;

}
    
}
