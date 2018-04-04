package com.rbc.petstore.api.repository;

import com.rbc.petstore.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findById(Long id);
    List<Pet> findByName(String name);

    List<Pet> findByStatusIn(List<Pet.Status> status);

}
