package com.travel.service;

import java.util.List;

import com.travel.dto.Hotels;

public interface HotelService {

    public List<Hotels> getAllHotels(int startIndex,int offset);
}
