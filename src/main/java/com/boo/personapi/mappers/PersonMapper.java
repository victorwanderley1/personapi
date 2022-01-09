package com.boo.personapi.mappers;

import com.boo.personapi.dto.request.PersonDTO;
import com.boo.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person personDTOtoPerson(PersonDTO dto);

    PersonDTO toDTO(Person person);
}
