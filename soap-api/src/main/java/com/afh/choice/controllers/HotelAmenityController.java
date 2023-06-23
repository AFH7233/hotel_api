package com.afh.choice.controllers;

import com.afh.choice.dto.Hotel;
import com.afh.choice.requests.HotelRequest;
import com.afh.choice.responses.HotelResponse;
import com.afh.choice.services.interfaces.HotelAmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Component
public class HotelAmenityController {

    private static final String NAMESPACE_URI = "encora:choice";

    private final HotelAmenityService hotelService;

    @Autowired
    public HotelAmenityController(HotelAmenityService hotelService) {
        this.hotelService = hotelService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HotelRequest")
    @ResponsePayload
    public HotelResponse processHotelRequest(@RequestPayload HotelRequest request) {
        HotelResponse response = new HotelResponse();
        if (request.getOperation().equalsIgnoreCase("create")) {
            hotelService.createHotel(request.getHotel());
            response.setMessage("Hotel created successfully.");
        } else if (request.getOperation().equalsIgnoreCase("get")) {
            Hotel hotel = hotelService.getHotelById(request.getHotelId());
            response.setHotel(hotel);
        } else if (request.getOperation().equalsIgnoreCase("update")) {
            hotelService.updateHotel(request.getHotel());
            response.setMessage("Hotel updated successfully.");
        } else if (request.getOperation().equalsIgnoreCase("delete")) {
            hotelService.deleteHotel(request.getHotelId());
            response.setMessage("Hotel deleted successfully.");
        } else if (request.getOperation().equalsIgnoreCase("addAmenity")) {
            hotelService.addAmenityToHotel(request.getHotelId(), request.getAmenityId());
            response.setMessage("Amenity added to the hotel successfully.");
        } else if (request.getOperation().equalsIgnoreCase("removeAmenity")) {
            hotelService.removeAmenityFromHotel(request.getHotelId(), request.getAmenityId());
            response.setMessage("Amenity removed from the hotel successfully.");
        }
        return response;
    }
}
