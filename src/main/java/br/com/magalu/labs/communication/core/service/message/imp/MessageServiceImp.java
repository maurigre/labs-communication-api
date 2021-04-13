package br.com.magalu.labs.communication.core.service.message.imp;

import br.com.magalu.labs.communication.core.exception.MessageValidException;
import br.com.magalu.labs.communication.core.model.Message;
import br.com.magalu.labs.communication.core.model.MessageState;
import br.com.magalu.labs.communication.core.repository.MessageRepository;
import br.com.magalu.labs.communication.core.service.destination.DestinationService;
import br.com.magalu.labs.communication.core.service.message.MessageService;
import br.com.magalu.labs.communication.core.service.validation.MessageValidationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;
    private final DestinationService destinationService;
    private final MessageValidationService messageValidationService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<Message> save(Message message) throws MessageValidException {
        message.setMessageState(MessageState.SCHEDULED);
        messageValidationService.isValidate(message);
        return destinationService.save(message.getDestination().getDestiny())
                .map(destination -> message.setDestination(destination))
                .map(messageRepository::save);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<Message> deleteById(Long id) {
        return messageRepository.findById(id)
                .map(message -> message.setMessageState(MessageState.DELETED))
                .map(messageRepository::save);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
