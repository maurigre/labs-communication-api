package br.com.magalu.labs.communication.core.service.message;

import br.com.magalu.labs.communication.core.dataprovider.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Optional<Message> save(Message message);

    Optional<Message> deleteById(Long id);

    Optional<Message> findById(Long id);

    List<Message> findAll();
}
