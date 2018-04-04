package com.rbc.petstore.api.repository;

import com.rbc.petstore.api.model.Pet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PetRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    @Test
    public void findById() throws Exception {
        Optional<Pet> result = petRepository.findById(1L);
        assertThat(result.isPresent(), is(true));
        assertThat(result.get().getId(), is(1L));
        assertThat(result.get().getName(), is("Doggie"));
        assertThat(result.get().getStatus(), is(Pet.Status.available));

    }

    @Test
    public void findByName() throws Exception {
        List<Pet> result = petRepository.findByName("Catty");
        assertThat(result, hasSize(1));
        assertThat(result.get(0).getName(), is("Catty"));
        assertThat(result.get(0).getStatus(), is(Pet.Status.available));
    }

    @Test
    public void findByStatusIn() throws Exception {
        List<Pet> result = petRepository.findByStatusIn(Arrays.asList(Pet.Status.available));
        assertThat(result, hasSize(2));
        assertThat(result.get(0).getStatus(), is(Pet.Status.available));
        assertThat(result.get(1).getStatus(), is(Pet.Status.available));
    }

}