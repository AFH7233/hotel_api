package com.afh.choice.services.interfaces;

import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;

import java.util.List;

public interface HotelAmenityService {

    HotelComplete createHotel(Hotel hotel);

    List<HotelComplete> searchHotelsByName(String query);

    HotelComplete updateHotel(Hotel hotel);

    void deleteHotel(String name);

    HotelComplete addAmenityToHotel(String hotelName, String amenityName);

    HotelComplete removeAmenityFromHotel(String hotelName, String amenityName);




}
