package com.afh.choice.controllers;


import com.afh.choice.services.interfaces.HotelAmenityService;
import com.afh.choice.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.websocket.MessageHandler;
import javax.xml.bind.JAXBElement;
import java.util.List;

@Endpoint
public class HotelAmenityController {

    private static final String NAMESPACE_URI = "http://afh.com/choice/soap";

    private final HotelAmenityService hotelAmenityService;


    public HotelAmenityController(@Autowired HotelAmenityService hotelService) {
        this.hotelAmenityService = hotelService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createHotelRequest")
    @ResponsePayload
    public CreateHotelResponse createHotel(@RequestPayload CreateHotelRequest request) {
        Hotel hotel = request.getHotel();
        HotelComplete hotelComplete = hotelAmenityService.createHotel(hotel);
        CreateHotelResponse response = new CreateHotelResponse();
        response.setHotel(hotelComplete);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateHotelRequest")
    @ResponsePayload
    public UpdateHotelResponse updateHotel(@RequestPayload UpdateHotelRequest request) {
        Hotel hotel = request.getHotel();
        HotelComplete hotelComplete = hotelAmenityService.updateHotel(hotel);
        UpdateHotelResponse response = new UpdateHotelResponse();
        response.setHotel(hotelComplete);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteHotelRequest")
    @ResponsePayload
    public DeleteHotelResponse deleteHotel(@RequestPayload DeleteHotelRequest request) {
        String name = request.getHotelName();
        hotelAmenityService.deleteHotel(name);
        DeleteHotelResponse response = new DeleteHotelResponse();
        response.setMessage("Hotel deleted successfully");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchHotelsByNameRequest")
    @ResponsePayload
    public SearchHotelsByNameResponse searchHotelsByName(@RequestPayload SearchHotelsByNameRequest request) {
        List<HotelComplete> hotels = hotelAmenityService.searchHotelsByName(request.getQuery());
        SearchHotelsByNameResponse response = new SearchHotelsByNameResponse();
        response.getHotels().addAll(hotels);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAmenityToHotelRequest")
    @ResponsePayload
    public AddAmenityToHotelResponse addAmenityToHotel(@RequestPayload AddAmenityToHotelRequest request) {
        HotelComplete hotelComplete = hotelAmenityService.addAmenityToHotel(request.getHotelName(), request.getAmenityName());
        AddAmenityToHotelResponse response = new AddAmenityToHotelResponse();
        response.setHotel(hotelComplete);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeAmenityFromHotelRequest")
    @ResponsePayload
    public RemoveAmenityFromHotelResponse removeAmenityFromHotel(@RequestPayload RemoveAmenityFromHotelRequest request) {
        HotelComplete hotelComplete = hotelAmenityService.removeAmenityFromHotel(request.getHotelName(), request.getAmenityName());
        RemoveAmenityFromHotelResponse response = new RemoveAmenityFromHotelResponse();
        response.setHotel(hotelComplete);
        return response;
    }
}
