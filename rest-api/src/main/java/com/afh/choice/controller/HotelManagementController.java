package com.afh.choice.controller;

import com.afh.choice.service.interfaces.HotelAmenityClient;
import com.afh.choice.soap.*;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling hotel requests.
 *
 * @author Andres Fuentes Hernandez
 */
@RestController
@RequestMapping("/api")
public class HotelManagementController {

    private final HotelAmenityClient hotelClient;

    public HotelManagementController(@Autowired HotelAmenityClient hotelClient) {
        this.hotelClient = hotelClient;
    }

    /**
     * Creates a hotel into the database.
     *
     * @param hotel The hotel to be created with a unique name (the name is trimmed and converted to lower case).
     * @return hotelComplete The hotel created plus the amenities assigned to it, it must be empty.
     */
    @PostMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelComplete> createHotel(@RequestBody Hotel hotel) {
        validateHotel(hotel);
        CreateHotelResponse response = hotelClient.createHotel(hotel);
        return ResponseEntity.ok(response.getHotel());
    }

    /**
     * Updates a hotel.
     *
     * @param hotel The hotel to be updated it uses the name to find the hotel to be updated.
     * @return hotelComplete The hotel updated plus the amenities assigned to it, it must be empty.
     */
    @PutMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelComplete> updateHotel(@RequestBody Hotel hotel) {
        validateHotel(hotel);
        UpdateHotelResponse response = hotelClient.updateHotel(hotel);
        return ResponseEntity.ok(response.getHotel());
    }

    /**
     * Deletes a hotel.
     *
     * @param hotelName The name of the hotel to be deleted.
     * @return A string if the operation was successful.
     */
    @DeleteMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteHotel(@RequestParam String hotelName) {
        DeleteHotelResponse response = hotelClient.deleteHotel(hotelName.trim().toLowerCase());
        return ResponseEntity.ok(response.getMessage());
    }

    /**
     * Query for hotels containing the query string as initial letters.
     *
     * @param query      The first letters of the name.
     * @param pageNumber The page number in which to search (Page size is 2 as default).
     * @return A list of hotels with their assigned amenities.
     */
    @GetMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HotelComplete>> searchHotelsByName(@RequestParam String query, @RequestParam int pageNumber) {
        SearchHotelsByNameResponse response = hotelClient.searchHotelsByName(query, pageNumber);
        return ResponseEntity.ok(response.getHotels());
    }

    /**
     * Adds an amenity from the list (pool, bar, meeting room, restaurant) to a Hotel.
     *
     * @param hotelName   The name of the hotel.
     * @param amenityName The name of the amenity.
     * @return The hotel with the amenities assigned to it.
     */
    @PostMapping(value = "/hotels/amenities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelComplete> addAmenityToHotel(
            @RequestParam String hotelName, @RequestParam String amenityName) {
        AddAmenityToHotelResponse response = hotelClient.addAmenityToHotel(hotelName, amenityName);
        return ResponseEntity.ok(response.getHotel());
    }

    /**
     * Removes an amenity from the list (pool, bar, meeting room, restaurant) to a Hotel.
     *
     * @param hotelName   The name of the hotel.
     * @param amenityName The name of the amenity.
     * @return The hotel with the amenities assigned to it.
     */
    @DeleteMapping(value = "/hotels/amenities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelComplete> removeAmenityToHotel(
            @RequestParam String hotelName, @RequestParam String amenityName) {
        RemoveAmenityFromHotelResponse response = hotelClient.removeAmenityFromHotel(hotelName, amenityName);
        return ResponseEntity.ok(response.getHotel());
    }

    private void validateHotel(Hotel hotel){
        if(hotel.getRating() < 0 || hotel.getRating() > 5){
            throw new IllegalArgumentException("Ratings must be numbers between 0 and 5.");
        }
        if(Objects.isNull(hotel.getName()) || hotel.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if(Objects.isNull(hotel.getAddress()) || hotel.getAddress().trim().isEmpty()){
            throw new IllegalArgumentException("Address cannot be empty.");
        }
    }
}
