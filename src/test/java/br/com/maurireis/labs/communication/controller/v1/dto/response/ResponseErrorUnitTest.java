package br.com.maurireis.labs.communication.controller.v1.dto.response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResponseErrorUnitTest {

    @Test
    void shouldCreatedResponseErrorBuilder(){
        ResponseError build = ResponseError.builder().build();
        Assertions.assertNotNull(build);

    }

}
