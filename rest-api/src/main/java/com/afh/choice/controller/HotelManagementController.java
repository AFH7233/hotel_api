package com.afh.choice.controller;

import com.afh.choice.service.interfaces.HotelAmenityClient;
import com.afh.choice.soap.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelManagementController {

  private final HotelAmenityClient hotelClient;

  public HotelManagementController(@Autowired HotelAmenityClient hotelClient) {
    this.hotelClient = hotelClient;
  }

  @PostMapping("/create")
  public ResponseEntity<String> createHotel(@RequestBody Hotel hotel) {
    CreateHotelResponse response = hotelClient.createHotel(hotel);
    return ResponseEntity.ok(response.getMessage());
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateHotel(@RequestBody Hotel hotel) {
    UpdateHotelResponse response = hotelClient.updateHotel(hotel);
    return ResponseEntity.ok(response.getMessage());
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteHotel(@RequestParam String hotelName) {
    DeleteHotelResponse response = hotelClient.deleteHotel(hotelName);
    return ResponseEntity.ok(response.getMessage());
  }

  @GetMapping("/search")
  public ResponseEntity<List<HotelComplete>> searchHotelsByName(@RequestParam String query) {
    SearchHotelsByNameResponse response = hotelClient.searchHotelsByName(query);
    return ResponseEntity.ok(response.getHotels());
  }

  @PostMapping("/add-amenity")
  public ResponseEntity<String> addAmenityToHotel(
      @RequestParam String hotelName, @RequestParam String amenityName) {
    AddAmenityToHotelResponse response = hotelClient.addAmenityToHotel(hotelName, amenityName);
    return ResponseEntity.ok(response.getMessage());
  }

  @PutMapping("/update-amenity")
  public ResponseEntity<String> updateAmenity(@RequestBody Amenity amenity) {
    UpdateAmenityResponse response = hotelClient.updateAmenity(amenity);
    return ResponseEntity.ok(response.getMessage());
  }

  @DeleteMapping("/delete-amenity")
  public ResponseEntity<String> deleteAmenity(@RequestParam String amenityName) {
    DeleteAmenityResponse response = hotelClient.deleteAmenity(amenityName);
    return ResponseEntity.ok(response.getMessage());
  }
}
