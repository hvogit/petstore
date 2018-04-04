package com.rbc.petstore.api.controller;

import com.rbc.petstore.api.exception.PetNotFoundException;
import com.rbc.petstore.api.model.Pet;
import com.rbc.petstore.api.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetRepository repo;

    public PetController(PetRepository repo) {
        this.repo = repo;
    }

    @GetMapping()
    public ResponseEntity<List<Pet>> list() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new PetNotFoundException());
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<Pet>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(repo.findByName(name));
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Pet>> findByStatuses(@RequestParam List<Pet.Status> status) {
        return ResponseEntity.ok(repo.findByStatusIn(status));
    }

    @PostMapping()
    public ResponseEntity<Pet> add(@RequestBody Pet p) {
        Pet pet = repo.save(new Pet(p.getName(), p.getStatus()));

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
                .buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(pet);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return repo.findById(id).map(pet -> {
            repo.delete(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new PetNotFoundException());
    }
}
