package br.com.magalu.labs.communication.core.exception;

import br.com.magalu.labs.communication.controller.v1.dto.response.ResponseErrorValid;
import org.springframework.http.HttpStatus;

/**
 * @author Mauri Reis
 * @since 13/04/21
 */
public class MessageValidException extends Exception{

    private int statusCode = HttpStatus.BAD_REQUEST.value();
    private ResponseErrorValid errorValid;

    public MessageValidException(ResponseErrorValid errorValid) {
        super(errorValid.getMessage());
        this.errorValid = errorValid;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseErrorValid getErrorValid() {
        return errorValid;
    }
}
