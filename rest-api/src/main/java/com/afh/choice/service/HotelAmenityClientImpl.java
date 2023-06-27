package com.afh.choice.service;

import com.afh.choice.service.interfaces.HotelAmenityClient;
import com.afh.choice.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;


/**
 * Hotel Client implementation of the methods that are present on the SOAP service.
 *
 * @author Andres Fuentes Hernandez
 */
@Service
public class HotelAmenityClientImpl implements HotelAmenityClient {

    @Value("${hotel.endpoint.url}")
    private String NAMESPACE_URI;

    private final WebServiceTemplate webServiceTemplate;

    public HotelAmenityClientImpl(@Autowired WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    /**
     * Creates a hotel into the database.
     *
     * @param hotel The hotel to be created with a unique name (the name is trimmed and converted to lower case).
     * @return CreateHotelResponse The mapped response sent by the SOAP service.
     */
    @Override
    public CreateHotelResponse createHotel(Hotel hotel) {
        CreateHotelRequest request = new CreateHotelRequest();
        request.setHotel(hotel);

        CreateHotelResponse response =
                (CreateHotelResponse) webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

        return response;
    }

    /**
     * Updates a hotel.
     *
     * @param hotel The hotel to be updated it uses the name to find the hotel to be updated.
     * @return UpdateHotelResponse The mapped response sent by the SOAP service.
     */
    @Override
    public UpdateHotelResponse updateHotel(Hotel hotel) {
        UpdateHotelRequest request = new UpdateHotelRequest();
        request.setHotel(hotel);

        UpdateHotelResponse response =
                (UpdateHotelResponse) webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

        return response;
    }

    /**
     * Deletes a hotel.
     *
     * @param hotelName The name of the hotel to be deleted.
     * @return DeleteHotelResponse The mapped response sent by the SOAP service.
     */
    @Override
    public DeleteHotelResponse deleteHotel(String hotelName) {
        DeleteHotelRequest request = new DeleteHotelRequest();
        request.setHotelName(hotelName);

        DeleteHotelResponse response =
                (DeleteHotelResponse) webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

        return response;
    }

    /**
     * Query for hotels containing the query string as initial letters.
     *
     * @param query      The first letters of the name.
     * @param pageNumber The page number in which to search (Page size is 2 as default).
     * @return SearchHotelsByNameResponse The mapped response sent by the SOAP service.
     */
    @Override
    public SearchHotelsByNameResponse searchHotelsByName(String query, int pageNumber) {
        SearchHotelsByNameRequest request = new SearchHotelsByNameRequest();
        request.setQuery(query);
        request.setPageNumber(pageNumber);

        SearchHotelsByNameResponse response =
                (SearchHotelsByNameResponse)
                        webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

        return response;
    }

    /**
     * Adds an amenity from the list (pool, bar, meeting room, restaurant) to a Hotel.
     *
     * @param hotelName   The name of the hotel.
     * @param amenityName The name of the amenity.
     * @return AddAmenityToHotelResponse The mapped response sent by the SOAP service.
     */
    @Override
    public AddAmenityToHotelResponse addAmenityToHotel(String hotelName, String amenityName) {
        AddAmenityToHotelRequest request = new AddAmenityToHotelRequest();
        request.setHotelName(hotelName);
        request.setAmenityName(amenityName);

        AddAmenityToHotelResponse response =
                (AddAmenityToHotelResponse)
                        webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

        return response;
    }

    /**
     * Removes an amenity from the list (pool, bar, meeting room, restaurant) to a Hotel.
     *
     * @param hotelName   The name of the hotel.
     * @param amenityName The name of the amenity.
     * @return RemoveAmenityFromHotelResponse The mapped response sent by the SOAP service.
     */
    @Override
    public RemoveAmenityFromHotelResponse removeAmenityFromHotel(String hotelName, String amenityName) {
        RemoveAmenityFromHotelRequest request = new RemoveAmenityFromHotelRequest();
        request.setHotelName(hotelName);
        request.setAmenityName(amenityName);

        RemoveAmenityFromHotelResponse response =
                (RemoveAmenityFromHotelResponse)
                        webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI, request);

        return response;
    }

}
