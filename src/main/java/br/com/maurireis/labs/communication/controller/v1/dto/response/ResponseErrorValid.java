package br.com.maurireis.labs.communication.controller.v1.dto.response;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ResponseErrorValid  extends ResponseError{

    private final String objectName;
    private final List<ResponseErrorValidField> errors;


}
