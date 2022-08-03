package br.com.maurireis.labs.communication.controller.v1.dto.response;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ResponseErrorValidUnitTest{

    @Test
    void shouldCreatedResponseErrorValidBuilder(){

        ResponseErrorValidField responseErrorValidField = new ResponseErrorValidField("Test error type", "type","teste");
        List<ResponseErrorValidField> errors = new ArrayList<>();
        errors.add(responseErrorValidField);

        ResponseErrorValid responseErrorValid = ResponseErrorValid.builder()
                .objectName("MessageDto")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .message("teste")
                .code(200)
                .status(HttpStatus.OK.name()).build();

        Assertions.assertNotNull(responseErrorValid);
        Assertions.assertEquals("ResponseErrorValid(objectName=MessageDto, errors=[ResponseErrorValidField(message=Test error type, field=type, parameter=teste)])", responseErrorValid.toString());
    }


}
