package com.afh.choice.service.interfaces;

import com.afh.choice.soap.*;

/**
 * Hotel Client contains the methods that are present on the SOAP service.
 *
 * @author Andres Fuentes Hernandez
 */
public interface HotelAmenityClient {
    CreateHotelResponse createHotel(Hotel hotel);

    UpdateHotelResponse updateHotel(Hotel hotel);

    DeleteHotelResponse deleteHotel(String hotelName);

    SearchHotelsByNameResponse searchHotelsByName(String query, int pageNumber);

    AddAmenityToHotelResponse addAmenityToHotel(String hotelName, String amenityName);

    RemoveAmenityFromHotelResponse removeAmenityFromHotel(String hotelName, String amenityName);

}
