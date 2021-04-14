package br.com.magalu.labs.communication.exception;

public class NotFoundMessageException extends RuntimeException {

    public NotFoundMessageException(Long id) {
        super("Menssagem nao localizada pelo id: " + id);
    }
}
