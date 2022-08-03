package br.com.maurireis.labs.communication.exception;

public class DelectedMessageFailException extends RuntimeException {

    public DelectedMessageFailException(Long id) {
        super("Falha ao deletar a mensagem de id: " + id);
    }
}
