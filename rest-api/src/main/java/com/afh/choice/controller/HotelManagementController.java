package com.afh.choice.controller;

import com.afh.choice.service.interfaces.HotelAmenityClient;
import com.afh.choice.soap.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HotelManagementController {

  private final HotelAmenityClient hotelClient;

  public HotelManagementController(@Autowired HotelAmenityClient hotelClient) {
    this.hotelClient = hotelClient;
  }

  @PostMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HotelComplete> createHotel(@RequestBody Hotel hotel) {
    CreateHotelResponse response = hotelClient.createHotel(hotel);
    return ResponseEntity.ok(response.getHotel());
  }

  @PutMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HotelComplete> updateHotel(@RequestBody Hotel hotel) {
    UpdateHotelResponse response = hotelClient.updateHotel(hotel);
    return ResponseEntity.ok(response.getHotel());
  }

  @DeleteMapping(value="/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> deleteHotel(@RequestParam String hotelName) {
    DeleteHotelResponse response = hotelClient.deleteHotel(hotelName);
    return ResponseEntity.ok(response.getMessage());
  }

  @GetMapping(value="/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<HotelComplete>> searchHotelsByName(@RequestParam String query) {
    SearchHotelsByNameResponse response = hotelClient.searchHotelsByName(query);
    return ResponseEntity.ok(response.getHotels());
  }

  @PostMapping(value="/hotels/amenities",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HotelComplete> addAmenityToHotel(
      @RequestParam String hotelName, @RequestParam String amenityName) {
    AddAmenityToHotelResponse response = hotelClient.addAmenityToHotel(hotelName, amenityName);
    return ResponseEntity.ok(response.getHotel());
  }

  @DeleteMapping(value="/hotels/amenities",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HotelComplete> removeAmenityToHotel(
          @RequestParam String hotelName, @RequestParam String amenityName) {
    RemoveAmenityFromHotelResponse response = hotelClient.removeAmenityFromHotel(hotelName, amenityName);
    return ResponseEntity.ok(response.getHotel());
  }

}
