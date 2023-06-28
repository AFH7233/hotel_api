package com.afh.choice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.afh.choice.dao.interfaces.AmenityDAO;
import com.afh.choice.dao.interfaces.HotelDAO;
import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.Hotel;
import com.afh.choice.soap.HotelComplete;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HotelAmenityServiceTest {

  @InjectMocks private HotelAmenityServiceImpl hotelAmenityService;

  @Mock private HotelDAO hotelDAO;

  @Mock private AmenityDAO amenityDAO;

  @Test
  public void createHotel() {
    Hotel hotel = new Hotel();
    hotel.setName("Hotel1");
    hotel.setAddress("Address1");
    hotel.setRating(5);

    HotelComplete hotelComplete = new HotelComplete();
    hotelComplete.setName(hotel.getName());
    hotelComplete.setAddress(hotel.getAddress());
    hotelComplete.setRating(hotel.getRating());

    when(hotelDAO.getHotelByName(anyString())).thenReturn(hotelComplete);

    HotelComplete result = hotelAmenityService.createHotel(hotel);

    assertEquals(hotel.getName(), result.getName());
    verify(hotelDAO, times(1)).createHotel(hotel);
  }

  @Test
  public void searchHotelsByName() {
    HotelComplete hotelComplete = new HotelComplete();
    hotelComplete.setName("Hotel1");

    when(hotelDAO.searchHotelsByName(anyString(), anyInt()))
        .thenReturn(Collections.singletonList(hotelComplete));

    List<HotelComplete> result = hotelAmenityService.searchHotelsByName("Hotel1", 1);

    assertFalse(result.isEmpty());
    assertEquals(hotelComplete.getName(), result.get(0).getName());
  }

  @Test
  public void updateHotel() {
    Hotel hotel = new Hotel();
    hotel.setName("Updated Hotel");
    hotel.setAddress("Address1");
    hotel.setRating(4);

    HotelComplete hotelComplete = new HotelComplete();
    hotelComplete.setName(hotel.getName());
    hotelComplete.setAddress(hotel.getAddress());
    hotelComplete.setRating(hotel.getRating());

    when(hotelDAO.getHotelByName(anyString())).thenReturn(hotelComplete);

    HotelComplete result = hotelAmenityService.updateHotel(hotel);

    assertEquals(hotel.getName(), result.getName());
    verify(hotelDAO, times(1)).updateHotel(hotel);
  }

  @Test
  public void deleteHotel() {
    doNothing().when(hotelDAO).deleteHotelByName(anyString());

    hotelAmenityService.deleteHotel("Hotel1");

    verify(hotelDAO, times(1)).deleteHotelByName("Hotel1");
  }

  @Test
  public void addAmenityToHotel() {
    HotelComplete hotelComplete = new HotelComplete();
    hotelComplete.setId(1L);
    hotelComplete.setName("Hotel1");

    Amenity amenity = new Amenity();
    amenity.setId(2L);
    amenity.setName("Pool");

    when(hotelDAO.getHotelByName(anyString())).thenReturn(hotelComplete);
    when(amenityDAO.getAmenityByName(anyString())).thenReturn(amenity);
    when(hotelDAO.getHotelByName(anyString())).thenReturn(hotelComplete);

    HotelComplete result = hotelAmenityService.addAmenityToHotel("Hotel1", "Pool");

    assertEquals(hotelComplete.getName(), result.getName());
    verify(hotelDAO, times(1)).addAmenityToHotel(hotelComplete.getId(), amenity.getId());
  }

  @Test
  public void removeAmenityFromHotel() {
    HotelComplete hotelComplete = new HotelComplete();
    hotelComplete.setId(1L);
    hotelComplete.setName("Hotel1");

    Amenity amenity = new Amenity();
    amenity.setId(2L);
    amenity.setName("Pool");

    when(hotelDAO.getHotelByName(anyString())).thenReturn(hotelComplete);
    when(amenityDAO.getAmenityByName(anyString())).thenReturn(amenity);

    when(hotelDAO.getHotelByName(anyString())).thenReturn(hotelComplete);

    HotelComplete result = hotelAmenityService.removeAmenityFromHotel("Hotel1", "Pool");

    assertEquals(hotelComplete.getName(), result.getName());
    verify(hotelDAO, times(1)).removeAmenityFromHotel(hotelComplete.getId(), amenity.getId());
  }
}
