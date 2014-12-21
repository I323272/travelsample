package com.travel.service;

import java.util.Map;

import com.travel.dto.HotelListData;

public interface HotelService {

    public HotelListData getAllHotels(int pageNo,int offset);
    public HotelListData getFilteredData(boolean filter,Map<String,String> param,int pageNo,int offset);
}
