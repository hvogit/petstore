package com.rbc.petstore.api.service;

import org.springframework.stereotype.Service;

/**
 * It is a best practice for Controller to call Service (not to call Repository directly).
 * However in this simple demo, there're no business logic, the Service will be bypassed.
 */
 @Service
 @Deprecated
public class PetService {
}
