package com.boo.personapi.services;

import com.boo.personapi.dto.MessageResponseDTO;
import com.boo.personapi.dto.request.PersonDTO;
import com.boo.personapi.entity.Person;
import com.boo.personapi.mappers.PersonMapper;
import com.boo.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository repository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    @Autowired
    public PersonService(final PersonRepository repository) {
        this.repository = repository;
    }

    public MessageResponseDTO createPerson(final PersonDTO personDTO) {
        Person saved = repository.save(personMapper.personDTOtoPerson(personDTO));
        return MessageResponseDTO.builder()
                .message(
                        "Person " + saved.getFirstName() + " " + saved.getLastName()
                                + " has been created with id: " + saved.getId())
                .build();
    }
}
