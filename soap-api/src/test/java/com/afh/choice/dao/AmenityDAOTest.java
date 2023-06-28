package com.afh.choice.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.afh.choice.config.HotelManagementInternalApplication;
import com.afh.choice.dao.interfaces.AmenityDAO;
import com.afh.choice.dao.interfaces.HotelDAO;
import com.afh.choice.soap.Amenity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = HotelManagementInternalApplication.class)
@ActiveProfiles("test")
@Transactional
public class AmenityDAOTest {

  @Autowired private AmenityDAO amenityDAO;

  @Test
  public void testGetAmenity() {
    String amenityName = "pool";
    Amenity amenity = this.amenityDAO.getAmenityByName(amenityName);
    assertEquals(amenityName, amenity.getName());
  }
}
