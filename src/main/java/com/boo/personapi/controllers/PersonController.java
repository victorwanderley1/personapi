package com.boo.personapi.controllers;

import com.boo.personapi.dto.MessageResponseDTO;
import com.boo.personapi.entity.Person;
import com.boo.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody final Person person) {
        return this.service.createPerson(person);
    }
}
