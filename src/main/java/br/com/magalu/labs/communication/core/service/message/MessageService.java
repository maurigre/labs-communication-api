package br.com.magalu.labs.communication.core.service.message;

import br.com.magalu.labs.communication.core.model.Message;

import java.util.List;

public interface MessageService {

    Message create(Message message);

    void deleteById(Long id);

    Message findById(Long id);

    List<Message> findAll();
}
