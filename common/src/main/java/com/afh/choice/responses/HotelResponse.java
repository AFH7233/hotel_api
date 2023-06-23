package com.afh.choice.responses;

import com.afh.choice.dto.Hotel;

public class HotelResponse {
    private String message;
    private Hotel hotel;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
