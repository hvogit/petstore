package com.rbc.petstore.api.exception;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException() {
        super("Pet not found");
    }
}
