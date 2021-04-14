package br.com.magalu.labs.communication.core.exception;

public class DelectedMessageFailException extends RuntimeException {

    public DelectedMessageFailException(Long id) {
        super("Falha ao deletar a mensagem de id: " + id);
    }
}
