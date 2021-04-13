package br.com.magalu.labs.communication.core.service.message;

import br.com.magalu.labs.communication.core.exception.MessageValidException;
import br.com.magalu.labs.communication.core.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Optional<Message> save(Message message) throws MessageValidException;

    Optional<Message> deleteById(Long id);

    Optional<Message> findById(Long id);

    List<Message> findAll();
}
