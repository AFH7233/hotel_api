package com.afh.choice.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.ws.soap.client.SoapFaultClientException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleSOAPExceptionDuplicateAmenityReturnsNotAcceptable() {
        String fault = "SQLIntegrityConstraintViolationException: HotelAmenities.PRIMARY";
        SoapFaultClientException ex = mock(SoapFaultClientException.class);
        when(ex.getFaultStringOrReason()).thenReturn(fault);

        ResponseEntity<?> response = globalExceptionHandler.handleSOAPException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertEquals("The amenity was already added to the hotel.", response.getBody());
    }

    @Test
    void handleSOAPExceptionDuplicateHotelNameReturnsNotAcceptable() {
        String fault = "SQLIntegrityConstraintViolationException: Duplicate entry for hotel name";
        SoapFaultClientException ex = mock(SoapFaultClientException.class);
        when(ex.getFaultStringOrReason()).thenReturn(fault);

        ResponseEntity<?> response = globalExceptionHandler.handleSOAPException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertEquals("The hotel name must be unique, all names are trimmed and converted to lower case.", response.getBody());
    }

    @Test
    void handleSOAPExceptionIncorrectResultSizeReturnsNotFound() {
        String fault = "Incorrect result size: expected 1, actual 0";
        SoapFaultClientException ex = mock(SoapFaultClientException.class);
        when(ex.getFaultStringOrReason()).thenReturn(fault);

        ResponseEntity<?> response = globalExceptionHandler.handleSOAPException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("The hotel/amenity does not exist.", response.getBody());
    }

    @Test
    void handleSOAPExceptionGeneralExceptionReturnsInternalServerError() {
        String fault = "General SOAP fault occurred";
        SoapFaultClientException ex = mock(SoapFaultClientException.class);
        when(ex.getFaultStringOrReason()).thenReturn(fault);

        ResponseEntity<?> response = globalExceptionHandler.handleSOAPException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(fault, response.getBody());
    }
}
