package com.travel.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

@Component(value = "hotelService")
public class HotelServiceImpl implements HotelService {

    private static List<Hotels> hotelArray = null;
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
        if (hotelArray != null && !hotelArray.isEmpty()
                && startLoc < hotelArray.size() - 1) {
            paginatedHotelList = new ArrayList<Hotels>();
            int noelements = hotelArray.size() - startLoc;
            if (noelements <= offset) {
                offset = noelements;
                moreResults=false;
            }
            for (int i = 0; i < offset; i++) {
                paginatedHotelList.add(hotelArray.get(startLoc+ i));
            }
        }
        hotelListData.setPaginatedHotelList(paginatedHotelList);
        hotelListData.setMoreResults(moreResults);

        return hotelListData;
    }

}
