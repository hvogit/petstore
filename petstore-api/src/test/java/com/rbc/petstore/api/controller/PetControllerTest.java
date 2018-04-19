package com.rbc.petstore.api.controller;

import com.rbc.petstore.api.exception.PetNotFoundException;
import com.rbc.petstore.api.model.Pet;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PetController.class, secure = false)
public class PetControllerTest {

    private final String API = "/api/pets";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PetController petController;

    @Test
    public void list() throws Exception {
        List<Pet> pets = Arrays.asList(new Pet("Catty", Pet.Status.available));
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

        given(petController.findById(0L)).willThrow(new PetNotFoundException());
        mvc.perform(get(API + "/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByName() throws Exception {
        List<Pet> pets = Arrays.asList(new Pet("Catty", Pet.Status.available));
        given(petController.findByName("Catty")).willReturn(ResponseEntity.ok(pets));
        mvc.perform(get(API).param("name", "Catty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Catty")))
                .andExpect(jsonPath("$[0].status", is("available")));

        given(petController.findByName("Doggie")).willReturn(ResponseEntity.ok(Lists.emptyList()));
        mvc.perform(get(API).param("name", "Doggie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void findByStatuses() throws Exception {
        List<Pet> pets = Arrays.asList(new Pet("Catty", Pet.Status.available));
        given(petController.findByStatuses(Arrays.asList(Pet.Status.available)))
                .willReturn(ResponseEntity.ok(pets));

        mvc.perform(get(API).param("status", "available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Catty")))
                .andExpect(jsonPath("$[0].status", is("available")));

        given(petController.findByStatuses(Arrays.asList(Pet.Status.sold)))
                .willReturn(ResponseEntity.ok(Lists.emptyList()));
    }

    @Test
    public void deletePet() throws Exception {
        given(petController.delete(1L)).willReturn(ResponseEntity.noContent().build());
        mvc.perform(delete(API + "/1"))
                .andExpect(status().isNoContent());

        given(petController.delete(2L)).willThrow(new PetNotFoundException());
        mvc.perform(delete(API + "/2"))
                .andExpect(status().isNotFound());
    }

}