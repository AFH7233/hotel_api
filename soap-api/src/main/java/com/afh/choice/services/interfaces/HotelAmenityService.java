package com.afh.choice.services.interfaces;

import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;

import java.util.List;

/**
 * Service layer for Hotel and Amenity CRUD.
 *
 * @author Andres Fuentes Hernandez
 */
public interface HotelAmenityService {

    HotelComplete createHotel(Hotel hotel);

    List<HotelComplete> searchHotelsByName(String query, int pageNumber);

    HotelComplete updateHotel(Hotel hotel);

    void deleteHotel(String name);

    HotelComplete addAmenityToHotel(String hotelName, String amenityName);

    HotelComplete removeAmenityFromHotel(String hotelName, String amenityName);


}
