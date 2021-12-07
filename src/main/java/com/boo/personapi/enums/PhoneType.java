package com.boo.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhoneType {

    HOME("Home"),
    MOBILE("Mobile"),
    COMERCIAL("Comercial");


    private final String description;
}
