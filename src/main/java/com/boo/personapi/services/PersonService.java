package com.boo.personapi.services;

import com.boo.personapi.dto.MessageResponseDTO;
import com.boo.personapi.dto.request.PersonDTO;
import com.boo.personapi.entity.Person;
import com.boo.personapi.exception.PersonNotFoundException;
import com.boo.personapi.mappers.PersonMapper;
import com.boo.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PersonDTO> listAll() {
        List<Person> allPeople =  repository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(final Long id) throws PersonNotFoundException {
        return personMapper.toDTO(this.verifyIfExists(id));
    }

    public MessageResponseDTO deletePerson(final Long id) throws PersonNotFoundException {
        this.repository.delete(this.verifyIfExists(id));
        return MessageResponseDTO.builder()
                .message("The Person with id: " + id + "has been exclude")
                .build();
    }

    private Person verifyIfExists(final Long id) throws PersonNotFoundException {
        return this.repository
                .findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
