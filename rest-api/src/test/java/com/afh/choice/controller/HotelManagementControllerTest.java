package com.afh.choice.controller;

import com.afh.choice.service.interfaces.HotelAmenityClient;
import com.afh.choice.soap.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HotelManagementControllerTest {

    @Mock
    private HotelAmenityClient hotelClient;

    private HotelManagementController hotelManagementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hotelManagementController = new HotelManagementController(hotelClient);
    }

    @Test
    void createHotelValidHotelReturnsHotelComplete() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        hotel.setAddress("123 Main St");
        hotel.setRating(4);

        HotelComplete hotelComplete = new HotelComplete();
        hotelComplete.setName(hotel.getName());
        hotelComplete.setAddress(hotel.getAddress());
        hotelComplete.setRating(hotel.getRating());

        CreateHotelResponse response = new CreateHotelResponse();
        response.setHotel(hotelComplete);
        when(hotelClient.createHotel(any(Hotel.class))).thenReturn(response);

        ResponseEntity<HotelComplete> result = hotelManagementController.createHotel(hotel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response.getHotel(), result.getBody());
    }

    @Test
    void updateHotelValidHotelReturnsHotelComplete() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        hotel.setAddress("123 Main St");
        hotel.setRating(4);

        HotelComplete hotelComplete = new HotelComplete();
        hotelComplete.setName(hotel.getName());
        hotelComplete.setAddress(hotel.getAddress());
        hotelComplete.setRating(hotel.getRating());

        UpdateHotelResponse response = new UpdateHotelResponse();
        response.setHotel(hotelComplete);

        when(hotelClient.updateHotel(any(Hotel.class))).thenReturn(response);

        ResponseEntity<HotelComplete> result = hotelManagementController.updateHotel(hotel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response.getHotel(), result.getBody());
    }

    @Test
    void deleteHotelValidHotelNameReturnsSuccessMessage() {
        String hotelName = "Hotel ABC";
        DeleteHotelResponse response = new DeleteHotelResponse();
        response.setMessage("Hotel deleted successfully.");

        when(hotelClient.deleteHotel(hotelName.trim().toLowerCase())).thenReturn(response);

        ResponseEntity<String> result = hotelManagementController.deleteHotel(hotelName);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response.getMessage(), result.getBody());
    }

    @Test
    void searchHotelsByNameValidQueryAndPageNumberReturnsListOfHotels() {
        String query = "Hotel";
        int pageNumber = 1;
        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        hotel.setAddress("123 Main St");
        hotel.setRating(4);

        HotelComplete hotelComplete = new HotelComplete();
        hotelComplete.setName(hotel.getName());
        hotelComplete.setAddress(hotel.getAddress());
        hotelComplete.setRating(hotel.getRating());

        List<HotelComplete> hotels = new ArrayList<>();
        hotels.add(hotelComplete);
        SearchHotelsByNameResponse response = new SearchHotelsByNameResponse();
        response.getHotels().addAll(hotels);

        when(hotelClient.searchHotelsByName(query, pageNumber)).thenReturn(response);

        ResponseEntity<List<HotelComplete>> result = hotelManagementController.searchHotelsByName(query, pageNumber);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(hotels, result.getBody());
    }

    @Test
    void addAmenityToHotelValidHotelAndAmenityReturnsHotelComplete() {
        String hotelName = "Hotel ABC";
        String amenityName = "pool";

        HotelComplete hotelComplete = new HotelComplete();
        hotelComplete.setName("Hotel ABC");
        hotelComplete.setAddress("123 Main St");
        hotelComplete.setRating(4);

        AddAmenityToHotelResponse response = new AddAmenityToHotelResponse();
        response.setHotel(hotelComplete);

        when(hotelClient.addAmenityToHotel(hotelName, amenityName)).thenReturn(response);

        ResponseEntity<HotelComplete> result = hotelManagementController.addAmenityToHotel(hotelName, amenityName);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response.getHotel(), result.getBody());
    }

    @Test
    void removeAmenityToHotelValidHotelAndAmenityReturnsHotelComplete() {
        String hotelName = "Hotel ABC";
        String amenityName = "pool";

        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        hotel.setAddress("123 Main St");
        hotel.setRating(4);

        HotelComplete hotelComplete = new HotelComplete();
        hotelComplete.setName(hotel.getName());
        hotelComplete.setAddress(hotel.getAddress());
        hotelComplete.setRating(hotel.getRating());

        RemoveAmenityFromHotelResponse response = new RemoveAmenityFromHotelResponse();
        response.setHotel(hotelComplete);

        when(hotelClient.removeAmenityFromHotel(hotelName, amenityName)).thenReturn(response);

        ResponseEntity<HotelComplete> result = hotelManagementController.removeAmenityToHotel(hotelName, amenityName);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response.getHotel(), result.getBody());
    }

    @Test
    void createHotelInvalidNameThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel();
        hotel.setName("");
        hotel.setAddress("123 Main St");
        hotel.setRating(3);

        assertThrows(IllegalArgumentException.class, () -> hotelManagementController.createHotel(hotel));
    }

    @Test
    void createHotelInvalidAddressThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        hotel.setAddress("");
        hotel.setRating(4);

        assertThrows(IllegalArgumentException.class, () -> hotelManagementController.createHotel(hotel));
    }

    @Test
    void createHotelInvalidRatingThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        hotel.setAddress("123 Main St");
        hotel.setRating(6);

        assertThrows(IllegalArgumentException.class, () -> hotelManagementController.createHotel(hotel));
    }

    @Test
    void createHotelDuplicateAmenityReturnsErrorMessage() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        hotel.setAddress("123 Main St");
        hotel.setRating(4);

        SoapFaultClientException exception = Mockito.mock(SoapFaultClientException.class);
        when(exception.getFaultStringOrReason()).thenReturn("HotelAmenities.PRIMARY");
        when(hotelClient.createHotel(any(Hotel.class))).thenThrow(exception);

        assertThrows(SoapFaultClientException.class, () -> hotelManagementController.createHotel(hotel));
    }

    @Test
    void addAmenityToHotelDuplicateHotelNameReturnsErrorMessage() {
        String hotelName = "Hotel ABC";
        String amenityName = "pool";

        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        hotel.setAddress("123 Main St");
        hotel.setRating(4);

        SoapFaultClientException exception = Mockito.mock(SoapFaultClientException.class);
        when(exception.getFaultStringOrReason()).thenReturn("");
        when(hotelClient.addAmenityToHotel(hotelName, amenityName)).thenThrow(exception);

        assertThrows(SoapFaultClientException.class, () -> hotelManagementController.addAmenityToHotel(hotelName, amenityName));

    }
}
