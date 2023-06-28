package com.afh.choice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.ws.soap.client.SoapFaultClientException;

/**
 * Holds common exceptions through the service.
 *
 * @author Andres Fuentes Hernandez
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SoapFaultClientException.class)
    public ResponseEntity<?> handleSOAPException(
            SoapFaultClientException ex, WebRequest request) {
        String fault = ex.getFaultStringOrReason();
        if (fault.contains("SQLIntegrityConstraintViolationException")) {
            if(fault.contains("HotelAmenities.PRIMARY")) {
                return new ResponseEntity<>("The amenity was already added to the hotel.", HttpStatus.NOT_ACCEPTABLE);
            } else {
                return new ResponseEntity<>("The hotel name must be unique, all names are trimmed and converted to lower case.", HttpStatus.NOT_ACCEPTABLE);
            }
        } else if (fault.contains("Incorrect result size")) {
            return new ResponseEntity<>("The hotel/amenity does not exist.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ex.getFaultStringOrReason(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}