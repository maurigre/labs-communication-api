package br.com.magalu.labs.communication.core.service.message.imp;

import br.com.magalu.labs.communication.core.dataprovider.enums.MessageState;
import br.com.magalu.labs.communication.core.dataprovider.model.Message;
import br.com.magalu.labs.communication.core.dataprovider.repository.MessageRepository;
import br.com.magalu.labs.communication.core.service.destination.DestinationService;
import br.com.magalu.labs.communication.core.service.message.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MessageServiceImp implements MessageService {

    MessageRepository messageRepository;
    DestinationService destinationService;

    @Override
    public Optional<Message> save(Message message) {
        return destinationService.save(message.getDestination().getDestiny())
                .map(destination -> message.setDestination(destination))
                .map(messageRepository::save);
    }

    @Override
    public Optional<Message> deleteById(Long id) {
        return messageRepository.findById(id)
                .map(message -> message.setMessageState(MessageState.DELETED))
                .map(messageRepository::save);
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
