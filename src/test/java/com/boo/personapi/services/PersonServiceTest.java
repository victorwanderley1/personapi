package com.boo.personapi.services;

import com.boo.personapi.dto.MessageResponseDTO;
import com.boo.personapi.dto.request.PersonDTO;
import com.boo.personapi.entity.Person;
import com.boo.personapi.exception.PersonNotFoundException;
import com.boo.personapi.repository.PersonRepository;
import com.boo.personapi.utils.PersonUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.boo.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;
    @Test
    void createPerson() {
        PersonDTO personDTO = createFakeDTO();
        Person person = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(person);

        MessageResponseDTO expectedMessageResponse = this.createMessageResponse(person);
        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        assertEquals(expectedMessageResponse, successMessage);
    }

    private MessageResponseDTO createMessageResponse(final Person person){
        return MessageResponseDTO
                .builder()
                .message("Person " + person.getFirstName() + " " + person.getLastName()
                        + " has been created with id: " + person.getId())
                .build();
    }

    @Test
    void listAll() {
        Person person = createFakeEntity();

        when(personRepository.findAll()).thenReturn(Collections.singletonList(person));
        List<PersonDTO> results = personService.listAll();
        Assertions.assertEquals(1, results.size());
    }

    @Test
    void findById() {
        Person person = createFakeEntity();
        Optional<Person> optionalPerson = Optional.of(person);
        when(personRepository.findById(1L)).thenReturn(optionalPerson);
        try {
            PersonDTO resultsPersonDTO = personService.findById(1L);
            Assertions.assertEquals(1L, resultsPersonDTO.getId());
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    void findByIdException(){
        when(personRepository.findById(1L)).thenReturn(Optional.empty());
        Exception e = Assertions.assertThrows(PersonNotFoundException.class,
                () -> personService.findById(1L));
        assertEquals("Not found Person with Id: " + 1L, e.getMessage());
    }

    @SneakyThrows
    @Test
    void deletePerson() {
        Person person = PersonUtils.createFakeEntity();
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        MessageResponseDTO messageActual = personService.deletePerson(1L);
        MessageResponseDTO messageExpected = MessageResponseDTO.builder()
                .message("The Person with id: (" + 1L + ") has been exclude").build();
        assertEquals(messageExpected, messageActual);
    }

    @SneakyThrows
    @Test
    void updateById() {
        Person person = createFakeEntity();
        PersonDTO personDTO = createFakeDTO();
        personDTO.setId(1L);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);

        MessageResponseDTO messageActual = personService.updateById(1L, personDTO);
        MessageResponseDTO messageExpected = MessageResponseDTO.builder()
                .message("Person " + personDTO.getFirstName() + " " + personDTO.getLastName()
                        + " has been updated with id: " + personDTO.getId()).build();

        assertEquals(messageExpected, messageActual);
    }
}