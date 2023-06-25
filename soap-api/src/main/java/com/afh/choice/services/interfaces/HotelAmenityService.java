package com.afh.choice.services.interfaces;

import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;

import java.util.List;

public interface HotelAmenityService {

    void createHotel(Hotel hotel);

    List<HotelComplete> searchHotelsByName(String query);

    void updateHotel(Hotel hotel);

    void deleteHotel(String name);

    void createAmenity(Amenity amenity);

    void updateAmenity(Amenity amenity);

    void deleteAmenity(String amenityName);

    void addAmenityToHotel(String hotelName, String amenityName);

    void removeAmenityToHotel(String hotelName, String amenityName);




}
