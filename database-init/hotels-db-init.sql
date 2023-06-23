-- Create the `hotels` table
CREATE TABLE IF NOT EXISTS Hotels (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    rating TINYINT CHECK(rating >= 0 AND rating <= 5)
);

-- Create the `amenities` table
CREATE TABLE IF NOT EXISTS Amenities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

-- Create the `hotel_amenities` joining table
CREATE TABLE IF NOT EXISTS HotelAmenities (
                                               hotel_id INT,
                                               amenity_id INT,
                                               PRIMARY KEY (hotel_id, amenity_id),
    FOREIGN KEY (hotel_id) REFERENCES Hotels(id) ON DELETE CASCADE,
    FOREIGN KEY (amenity_id) REFERENCES Amenities(id) ON DELETE CASCADE
    );

-- Procedure to create a hotel
DELIMITER $$

CREATE PROCEDURE create_hotel(IN hotel_name VARCHAR(255), IN hotel_address VARCHAR(255), IN hotel_rating TINYINT)
BEGIN
    INSERT INTO Hotels (name, address, rating)
    VALUES (hotel_name, hotel_address, hotel_rating);
END$$

-- Procedure to delete a hotel
CREATE PROCEDURE delete_hotel(IN hotel_id INT)
BEGIN
    DELETE FROM Hotels WHERE id = hotel_id;
END$$

-- Procedure to update a hotel
CREATE PROCEDURE update_hotel(IN hotel_id INT, IN hotel_name VARCHAR(255), IN hotel_address VARCHAR(255), IN hotel_rating TINYINT)
BEGIN
    UPDATE Hotels SET
        name = hotel_name,
        address = hotel_address,
        rating = hotel_rating
    WHERE id = hotel_id;
END$$

-- Procedure to get a hotel by id
CREATE PROCEDURE get_hotel_by_id(IN hotel_id INT)
BEGIN
    SELECT * FROM Hotels WHERE id = hotel_id;
END$$


-- Procedure to get all hotels with a name like the given query
CREATE PROCEDURE get_hotels_by_name(IN search_query VARCHAR(255))
BEGIN
    SELECT * FROM Hotels WHERE name LIKE CONCAT('%', search_query, '%');
END$$

-- Procedure to create an amenity
CREATE PROCEDURE create_amenity(IN amenity_name VARCHAR(255), IN amenity_description VARCHAR(255))
BEGIN
INSERT INTO Amenities (name, description)
VALUES (amenity_name, amenity_description);
END$$

-- Procedure to update an amenity
CREATE PROCEDURE update_amenity(IN amenity_id INT, IN amenity_name VARCHAR(255), IN amenity_description VARCHAR(255))
BEGIN
UPDATE Amenities SET
                     name = amenity_name,
                     description = amenity_description
WHERE id = amenity_id;
END$$

-- Procedure to delete an amenity
CREATE PROCEDURE delete_amenity(IN amenity_id INT)
BEGIN
DELETE FROM Amenities WHERE id = amenity_id;
END$$

-- Procedure to get an amenity by id
CREATE PROCEDURE get_amenity_by_id(IN amenity_id INT)
BEGIN
SELECT * FROM Amenities WHERE id = amenity_id;
END$$

-- Procedure to get amenities by hotel ID
CREATE PROCEDURE get_amenities_by_hotel(IN p_hotel_id INT)
BEGIN
SELECT
    Amenities.id AS amenity_id,
    Amenities.name AS amenity_name,
    Amenities.description AS amenity_description
FROM
    Amenities
        JOIN
    Hotel_Amenities ON Amenities.id = Hotel_Amenities.amenity_id
WHERE
        Hotel_Amenities.hotel_id = p_hotel_id;
END$$

-- Procedure to add an amenity to a hotel
CREATE PROCEDURE add_amenity_to_hotel(IN hotel_id INT, IN amenity_id INT)
BEGIN
INSERT INTO HotelAmenities (hotel_id, amenity_id)
VALUES (hotel_id, amenity_id);
END$$

-- Procedure to remove an amenity from a hotel
CREATE PROCEDURE remove_amenity_from_hotel(IN hotel_id INT, IN amenity_id INT)
BEGIN
DELETE FROM HotelAmenities
WHERE hotel_id = hotel_id
  AND amenity_id = amenity_id;
END$$
