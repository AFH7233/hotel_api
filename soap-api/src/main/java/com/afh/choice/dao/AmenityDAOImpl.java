package com.afh.choice.dao;

import com.afh.choice.dao.interfaces.AmenityDAO;
import com.afh.choice.soap.Amenity;
import com.afh.choice.soap.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class AmenityDAOImpl implements AmenityDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public AmenityDAOImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void createAmenity(Amenity amenity) {
    if (Objects.isNull(amenity.getId())){
      jdbcTemplate.update("CALL create_amenity(?, ?)", amenity.getName(), amenity.getDescription());
    }
  }

  public void updateAmenity(Amenity amenity) {
    jdbcTemplate.update(
        "CALL update_amenity(?, ?, ?)",
        amenity.getId(),
        amenity.getName(),
        amenity.getDescription());
  }

  @Override
  public void deleteAmenityByName(String amenityName) {
    jdbcTemplate.update("CALL delete_amenity_by_name(?)", amenityName);
  }

  @Override
  public Amenity getAmenityByName(String amenityName) {
    Amenity amenityResult =
            jdbcTemplate.queryForObject(
                    "CALL get_amenity_by_by_name(?)",
                    new Object[] {amenityName},
                    (rs, rowNum) -> {
                      Amenity amenity = new Amenity();
                      amenity.setName(rs.getString("name"));
                      amenity.setId(rs.getLong("id"));
                      amenity.setDescription("description");
                      return amenity;
                    });
    return amenityResult;
  }

  public void deleteAmenityByName(long amenityId) {
    jdbcTemplate.update("CALL delete_amenity(?)", amenityId);
  }
}
