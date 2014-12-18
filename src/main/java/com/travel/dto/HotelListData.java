package com.travel.dto;

import java.util.List;

public class HotelListData {
 private List<Hotels> paginatedHotelList;
 private boolean moreResults;
public List<Hotels> getPaginatedHotelList() {
    return paginatedHotelList;
}
public void setPaginatedHotelList(List<Hotels> paginatedHotelList) {
    this.paginatedHotelList = paginatedHotelList;
}
public boolean isMoreResults() {
    return moreResults;
}
public void setMoreResults(boolean moreResults) {
    this.moreResults = moreResults;
}
}
