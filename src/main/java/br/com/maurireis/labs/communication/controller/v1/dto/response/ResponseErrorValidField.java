package br.com.maurireis.labs.communication.controller.v1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ResponseErrorValidField {

    private final String message;
    private final String field;
    private final Object parameter;
}
