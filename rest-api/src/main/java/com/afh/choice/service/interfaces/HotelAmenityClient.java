package com.afh.choice.service.interfaces;

import com.afh.choice.soap.*;

public interface HotelAmenityClient {
    CreateHotelResponse createHotel(Hotel hotel);

    UpdateHotelResponse updateHotel(Hotel hotel);

    DeleteHotelResponse deleteHotel(String hotelName);

    SearchHotelsByNameResponse searchHotelsByName(String query);

    AddAmenityToHotelResponse addAmenityToHotel(String hotelName, String amenityName);

    UpdateAmenityResponse updateAmenity(Amenity amenity);

    DeleteAmenityResponse deleteAmenity(String amenityName);
}
