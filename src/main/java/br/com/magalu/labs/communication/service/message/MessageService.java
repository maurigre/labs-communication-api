package br.com.magalu.labs.communication.service.message;

import br.com.magalu.labs.communication.dataprovider.model.Message;

import java.util.List;

public interface MessageService {

    Message create(Message message);

    void deleteById(Long id);

    Message findById(Long id);

    List<Message> findAll();
}
