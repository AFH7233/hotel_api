package com.afh.choice.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.afh.choice.config.HotelManagementInternalApplication;
import com.afh.choice.dao.interfaces.AmenityDAO;
import com.afh.choice.dao.interfaces.HotelDAO;
import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = HotelManagementInternalApplication.class)
@ActiveProfiles("test")
@Transactional
public class HotelDAOTest {
  @Autowired private HotelDAO hotelDAO;

  @Autowired private AmenityDAO amenityDAO;

  private Hotel testHotel;

  @BeforeEach
  public void setup() {
    testHotel = new Hotel();
    testHotel.setName("Test Hotel");
    testHotel.setAddress("123 Test St.");
    testHotel.setRating(5);

    hotelDAO.createHotel(testHotel);
  }

  @AfterEach
  public void cleanup() {
    hotelDAO.deleteHotelByName("Test Hotel");
  }

  @Test
  public void testCreateHotel() {
    String name = "Another Hotel".toLowerCase();
    String address = "456 Test St.";
    int rating = 4;
    Hotel newHotel = new Hotel();
    newHotel.setName(name);
    newHotel.setAddress(address);
    newHotel.setRating(rating);

    hotelDAO.createHotel(newHotel);

    HotelComplete retrievedHotel = hotelDAO.getHotelByName(name);
    assertEquals(name, retrievedHotel.getName());
    assertEquals(address, retrievedHotel.getAddress());
    assertEquals(rating, retrievedHotel.getRating());

    hotelDAO.deleteHotelByName(name);
  }

  @Test
  public void testUpdateHotel() {
    String addressChange = "789 Updated St.";
    testHotel.setAddress(addressChange);
    hotelDAO.updateHotel(testHotel);

    HotelComplete updatedHotel = hotelDAO.getHotelByName(testHotel.getName());
    assertEquals(addressChange, updatedHotel.getAddress());
  }

  @Test
  public void testSearchHotelsByName() {
    List<HotelComplete> hotels = hotelDAO.searchHotelsByName("Test", 1);
    assertTrue(hotels.size() > 0);
  }

  @Test
  public void testAddRemoveAmenityToHotel() {
    HotelComplete hotel = this.hotelDAO.getHotelByName(testHotel.getName());
    Amenity amenity = this.amenityDAO.getAmenityByName("pool");

    this.hotelDAO.addAmenityToHotel(hotel.getId(), amenity.getId());
    HotelComplete hotelWithPool = this.hotelDAO.getHotelByName(testHotel.getName());

    long pools =
        hotelWithPool.getAmenities().getAmenity().stream()
            .filter(e -> e.getName().equals("pool"))
            .count();
    assertTrue(pools == 1);

    this.hotelDAO.removeAmenityFromHotel(hotel.getId(), amenity.getId());
    HotelComplete hotelWithout = this.hotelDAO.getHotelByName(testHotel.getName());

    pools =
        hotelWithout.getAmenities().getAmenity().stream()
            .filter(e -> e.getName().equals("pool"))
            .count();
    assertTrue(pools == 0);
  }
}
