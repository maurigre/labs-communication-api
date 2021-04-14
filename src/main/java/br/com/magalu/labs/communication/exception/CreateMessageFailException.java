package br.com.magalu.labs.communication.exception;

public class CreateMessageFailException extends RuntimeException {

    public CreateMessageFailException() {
        super("Falha ao registrar a mensagem");
    }
}
