package com.afh.choice.dao;

import com.afh.choice.dao.interfaces.AmenityDAO;
import com.afh.choice.soap.Amenity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Data access implementation layer for Amenity CRUD.
 * <p>
 * It calls the procedures created on the DB.
 *
 * @author Andres Fuentes Hernandez
 */
@Repository
public class AmenityDAOImpl implements AmenityDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AmenityDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Amenity getAmenityByName(String amenityName) {
        Amenity amenityResult =
                jdbcTemplate.queryForObject(
                        "CALL get_amenity_by_by_name(?)",
                        new Object[]{amenityName},
                        (rs, rowNum) -> {
                            Amenity amenity = new Amenity();
                            amenity.setName(rs.getString("name"));
                            amenity.setId(rs.getLong("id"));
                            amenity.setDescription("description");
                            return amenity;
                        });
        return amenityResult;
    }

}
