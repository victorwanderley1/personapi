package com.boo.personapi.utils;

import com.boo.personapi.dto.request.PersonDTO;
import com.boo.personapi.entity.Person;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {
    private static final String FIRST_NAME = "Xeresvaldo";
    private static final String LAST_NAME = "Smith";
    private static final String CPF_NUMBER = "494.471.910-88";
    private static final long  PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(1996, 6, 22);

    public static PersonDTO createFakeDTO(){
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate("22-06-1996")
                .phone(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity(){
        return Person.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(BIRTH_DATE)
                .id(PERSON_ID)
                .phone(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }

}
