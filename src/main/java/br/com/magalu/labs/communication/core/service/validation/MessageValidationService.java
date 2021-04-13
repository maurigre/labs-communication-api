package br.com.magalu.labs.communication.core.service.validation;

import br.com.magalu.labs.communication.controller.v1.dto.response.ResponseErrorValid;
import br.com.magalu.labs.communication.core.exception.MessageValidException;
import br.com.magalu.labs.communication.core.model.Message;

/**
 * @author Mauri Reis
 * @since 13/04/21
 */
public interface MessageValidationService {


    boolean isValidate(Message message) throws MessageValidException;

    ResponseErrorValid getErrorValid();

}
