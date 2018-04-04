package com.rbc.petstore.api.controller;

import com.rbc.petstore.api.exception.PetNotFoundException;
import com.rbc.petstore.api.model.Pet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PetController.class, secure = false)
//@WebAppConfiguration
//@SpringBootTest(classes = PetstoreApiApplication.class)
public class PetControllerTest {

    private final String API = "/api/pets";

//    @Autowired
//    private WebApplicationContext wac;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PetController petController;

    @Test
    public void list() throws Exception {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("Catty", Pet.Status.available));
        given(petController.list()).willReturn(ResponseEntity.ok(pets));

        mvc.perform(get(API))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Catty")))
                .andExpect(jsonPath("$[0].status", is("available")));
    }

    @Test
    public void findById() throws Exception {
        Pet pet = new Pet(1L, "Doggie", Pet.Status.sold);
        given(petController.findById(1L)).willReturn(ResponseEntity.ok(pet));

        mvc.perform(get(API + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Doggie")))
                .andExpect(jsonPath("$.status", is("sold")));

        given(petController.findById(0L)).willThrow(new PetNotFoundException(0L));
        mvc.perform(get(API + "/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByName() throws Exception {
    }

    @Test
    public void findByStatuses() throws Exception {
    }

    @Test
    public void add() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}