package br.com.maurireis.labs.communication.exception;

public class NotFoundMessageException extends RuntimeException {

    public NotFoundMessageException(Long id) {
        super("Menssagem nao localizada pelo id: " + id);
    }
}
