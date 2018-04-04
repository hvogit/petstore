package com.rbc.petstore.api.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Status status;

    // private String[] photoUrls;
    // private Tag tag;
    // private Category category;

    public enum Status {
        available, pending, sold
    }

    public Pet() {
    }

    public Pet(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Pet(String name, Status status) {
        this(null, name, status);
    }

    public Pet(String name) {
        this(null, name, Status.available);
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Pet (id: %d, name: '%s', status: %s)", id, name, status);
    }
}
