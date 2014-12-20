package com.travel.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.travel.dto.HotelListData;
import com.travel.dto.Hotels;
import com.travel.service.HotelService;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

@Component(value = "hotelService")
public class HotelServiceImpl implements HotelService {

    private static List<Hotels> hotelArray = null;
    private List<Hotels> filteredList = hotelArray;
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
                && startLoc < filteredList.size() - 1) {
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
    public HotelListData getFilteredData(boolean filter, String param,
            String data, int pageNo, int offset) {
        if (!filter) {
            filteredList = hotelArray;
        } else {
            List<Hotels> list=new ArrayList<Hotels>();
            if (null != filteredList && !filteredList.isEmpty()) {
                for (int i = 0; i < filteredList.size(); i++) {
                    try {
                        if (StringUtils.containsIgnoreCase((String)(PropertyUtils.getProperty(filteredList.get(i), param)), data)){
                            list.add(filteredList.get(i));
                        }
                    } catch (IllegalAccessException | InvocationTargetException
                            | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                filteredList=list;
            }
            for (int i = 0; i < filteredList.size(); i++) {
               System.out.println(filteredList.get(i).getCity());
            }
        }
        HotelListData hotelListData = getAllHotels(pageNo, offset);
        return hotelListData;
    }

}
