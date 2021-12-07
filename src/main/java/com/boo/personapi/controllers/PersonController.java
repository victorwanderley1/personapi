package com.boo.personapi.controllers;

import com.boo.personapi.dto.MessageResponseDTO;
import com.boo.personapi.entity.Person;
import com.boo.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody final Person person){
        Person saved = repository.save(person);
        return MessageResponseDTO.builder()
                .message(
                        "Person "+saved.getFirstName()+" "+saved.getLastName()
                                +" has been created whit id: "+saved.getId())
                .build();
    }
}
