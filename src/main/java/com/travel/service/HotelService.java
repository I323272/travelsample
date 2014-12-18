package com.travel.service;

import com.travel.dto.HotelListData;

public interface HotelService {

    public HotelListData getAllHotels(int startIndex,int offset);
}
