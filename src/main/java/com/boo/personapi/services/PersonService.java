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
        return this.createMessageResponse(
                        "Person " + saved.getFirstName() + " " + saved.getLastName()
                                + " has been created with id: " + saved.getId());
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople =  repository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(final Long id) throws PersonNotFoundException {
        return personMapper.toDTO(this.ifNotExistsThrowException(id));
    }

    public MessageResponseDTO deletePerson(final Long id) throws PersonNotFoundException {
        this.repository.delete(this.ifNotExistsThrowException(id));
        return this.createMessageResponse("The Person with id: (" + id + ") has been exclude");
    }

    public MessageResponseDTO updateById(final Long id, final PersonDTO personDTO) throws PersonNotFoundException {
        this.ifNotExistsThrowException(id);
        Person saved = this.repository.save(personMapper.personDTOtoPerson(personDTO));
        return createMessageResponse(
                "Person " + saved.getFirstName() + " " + saved.getLastName()
                        + " has been updated with id: " + saved.getId());
    }

    private Person ifNotExistsThrowException(final Long id) throws PersonNotFoundException {
        return this.repository
                .findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(final String message){
        return MessageResponseDTO.builder().message(message).build();
    }
}
