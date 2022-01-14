package com.boo.personapi.utils;

import com.boo.personapi.dto.request.PhoneDTO;
import com.boo.personapi.entity.Phone;
import com.boo.personapi.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "8499999-9999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO(){
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity(){
        return Phone.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .id(PHONE_ID)
                .build();
    }


}
