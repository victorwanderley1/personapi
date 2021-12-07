package com.boo.personapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageResponseDTO {
    private String message;
}
