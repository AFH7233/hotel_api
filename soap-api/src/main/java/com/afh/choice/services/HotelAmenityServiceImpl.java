package com.afh.choice.services;

import com.afh.choice.dao.interfaces.AmenityDAO;
import com.afh.choice.dao.interfaces.HotelDAO;
import com.afh.choice.services.interfaces.HotelAmenityService;
import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer implementation for Hotel and Amenity CRUD.
 *
 * @author Andres Fuentes Hernandez
 */
@Service
public class HotelAmenityServiceImpl implements HotelAmenityService {

  private final HotelDAO hotelDAO;

  private final AmenityDAO amenityDAO;

  public HotelAmenityServiceImpl(@Autowired HotelDAO hotelDAO, @Autowired AmenityDAO amenityDAO) {
    this.hotelDAO = hotelDAO;
    this.amenityDAO = amenityDAO;
  }

  @Override
  public HotelComplete createHotel(Hotel hotel) {
    this.hotelDAO.createHotel(hotel);
    HotelComplete hotelComplete = this.hotelDAO.getHotelByName(hotel.getName());
    return hotelComplete;
  }

  @Override
  public List<HotelComplete> searchHotelsByName(String query, int pageNumber)  {
    return this.hotelDAO.searchHotelsByName(query, pageNumber);
  }

  @Override
  public HotelComplete updateHotel(Hotel hotel) {
    this.hotelDAO.updateHotel(hotel);
    HotelComplete hotelComplete = this.hotelDAO.getHotelByName(hotel.getName());
    hotelComplete.setName(hotel.getName());
    hotelComplete.setAddress(hotel.getAddress());
    hotelComplete.setRating(hotel.getRating());
    return hotelComplete;
  }

  @Override
  public void deleteHotel(String name) {
    this.hotelDAO.deleteHotelByName(name);
  }

  @Override
  public HotelComplete addAmenityToHotel(String hotelName, String amenityName) {
    HotelComplete hotel = this.hotelDAO.getHotelByName(hotelName);
    Amenity amenity = this.amenityDAO.getAmenityByName(amenityName);
    this.hotelDAO.addAmenityToHotel(hotel.getId(), amenity.getId());
    HotelComplete hotelComplete = this.hotelDAO.getHotelByName(hotel.getName());
    return hotelComplete;
  }

  @Override
  public HotelComplete removeAmenityFromHotel(String hotelName, String amenityName) {
    HotelComplete hotel = this.hotelDAO.getHotelByName(hotelName);
    Amenity amenity = this.amenityDAO.getAmenityByName(amenityName);
    this.hotelDAO.removeAmenityFromHotel(hotel.getId(), amenity.getId());
    HotelComplete hotelComplete = this.hotelDAO.getHotelByName(hotel.getName());
    return hotelComplete;
  }
}
