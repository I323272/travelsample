package com.travel.service;

import java.util.Map;

import com.travel.dto.HotelListData;

/**
 * This Interface provides the API for retrieving the list of filtered data
 */
public interface HotelService {

    /**
     * This method provides the list of travel Deals in pages for the filtered data
     */
    public HotelListData getAllHotels(int pageNo,int offset) throws Exception;
    
    /**
     * This method filters the data based on different params in the form
     */
    public HotelListData getFilteredData(boolean filter,Map<String,String> param,int pageNo,int offset) throws Exception;
}
