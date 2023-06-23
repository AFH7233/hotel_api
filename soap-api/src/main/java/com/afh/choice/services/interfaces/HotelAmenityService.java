package com.afh.choice.services.interfaces;

import com.afh.choice.dto.Hotel;

public interface HotelAmenityService {
    void createHotel(Hotel hotel);

    Hotel getHotelById(long hotelId);

    void updateHotel(Hotel hotel);

    void deleteHotel(long hotelId);

    void addAmenityToHotel(long hotelId, long amenityId);

    void removeAmenityFromHotel(long hotelId, long amenityId);
}
