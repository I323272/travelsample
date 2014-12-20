package com.travel.service;

import com.travel.dto.HotelListData;

public interface HotelService {

    public HotelListData getAllHotels(int pageNo,int offset);
    public HotelListData getFilteredData(boolean filter,String param,String data,int pageNo,int offset);
}
