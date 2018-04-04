package com.rbc.petstore.api.controller;

import com.rbc.petstore.api.exception.PetNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class ErrorHandler {
    Log log = LogFactory.getLog(getClass());
    private final MediaType errorMediaType = MediaType.parseMediaType("application/error+json");

    @ExceptionHandler(PetNotFoundException.class)
    ResponseEntity<Error> resourceNotFoundException(PetNotFoundException e) {
        return this.error(e, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Error> error(Exception e, HttpStatus status) {
        Error error = new Error(e.getMessage(), UUID.randomUUID().toString());
        log.error(error.getLogRef(), e);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(errorMediaType);
        return new ResponseEntity(error, headers, status);
    }

    /**
     * Represents standard error response
     */
    private class Error {

        private String message;
        private String logRef;

        public Error(String message, String logRef) {
            this.message = message;
            this.logRef = logRef;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getLogRef() {
            return logRef;
        }

        public void setLogRef(String logRef) {
            this.logRef = logRef;
        }

        @Override
        public String toString() {
            return "Error {" +
                    "message='" + message + '\'' +
                    ", logRef='" + logRef + '\'' +
                    '}';
        }
    }
}
