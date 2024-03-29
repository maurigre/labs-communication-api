package br.com.maurireis.labs.communication.controller.v1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ResponseErrorValidField {

    private final String message;
    private final String field;
    private final Object parameter;
}
