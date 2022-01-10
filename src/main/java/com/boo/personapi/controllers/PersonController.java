package com.boo.personapi.controllers;

import com.boo.personapi.dto.MessageResponseDTO;
import com.boo.personapi.dto.request.PersonDTO;
import com.boo.personapi.entity.Person;
import com.boo.personapi.exception.PersonNotFoundException;
import com.boo.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public MessageResponseDTO createPerson(@RequestBody @Valid final PersonDTO personDTO) {
        return this.service.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll(){
        return this.service.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable final Long id) throws PersonNotFoundException {
        return this.service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO deletePerson(@PathVariable final Long id) throws PersonNotFoundException {
        return this.service.deletePerson(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO updateById(@PathVariable final Long id, @RequestBody @Valid final PersonDTO personDTO) throws PersonNotFoundException {
        return this.service.updateById(id, personDTO);
    }
}
