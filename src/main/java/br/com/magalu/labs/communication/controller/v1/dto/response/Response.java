package br.com.magalu.labs.communication.controller.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;

}
