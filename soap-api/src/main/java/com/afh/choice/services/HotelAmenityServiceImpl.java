package com.afh.choice.services;

import com.afh.choice.dto.Hotel;
import com.afh.choice.services.interfaces.HotelAmenityService;
import org.springframework.stereotype.Service;

@Service
public class HotelAmenityServiceImpl implements HotelAmenityService {
    @Override
    public void createHotel(Hotel hotel) {

    }

    @Override
    public Hotel getHotelById(long hotelId) {
        return null;
    }

    @Override
    public void updateHotel(Hotel hotel) {

    }

    @Override
    public void deleteHotel(long hotelId) {

    }

    @Override
    public void addAmenityToHotel(long hotelId, long amenityId) {

    }

    @Override
    public void removeAmenityFromHotel(long hotelId, long amenityId) {

    }
}
