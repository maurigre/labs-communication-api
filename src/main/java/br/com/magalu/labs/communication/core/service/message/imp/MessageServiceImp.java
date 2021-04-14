package br.com.magalu.labs.communication.core.service.message.imp;

import br.com.magalu.labs.communication.core.exception.CreateMessageFailException;
import br.com.magalu.labs.communication.core.exception.DelectedMessageFailException;
import br.com.magalu.labs.communication.core.exception.NotFoundMessageException;
import br.com.magalu.labs.communication.core.model.Message;
import br.com.magalu.labs.communication.core.model.MessageState;
import br.com.magalu.labs.communication.core.repository.MessageRepository;
import br.com.magalu.labs.communication.core.service.destination.DestinationService;
import br.com.magalu.labs.communication.core.service.message.MessageService;
import br.com.magalu.labs.communication.core.service.rabbitmq.RabbitMqService;
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
    private final RabbitMqService rabbitMqService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Message create(Message message) {
        final Optional<Message> messageCreated =
                destinationService.create(message.getDestination().getDestiny())
                        .map(destination -> message.setDestination(destination))
                        .map(message1 -> message1.setMessageState(MessageState.SCHEDULED))
                        .map(messageRepository::save);
        messageCreated.ifPresent(rabbitMqService::producer);
        if (messageCreated.isPresent()) {
            return messageCreated.get();
        } else throw new CreateMessageFailException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        final Optional<Message> messageOptional = messageRepository.findById(id)
                .map(message -> message.setMessageState(MessageState.DELETED))
                .map(messageRepository::save);
        messageOptional.ifPresent(rabbitMqService::producer);
        if(!messageOptional.isPresent())
            throw new DelectedMessageFailException(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Message findById(Long id) {
       final Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()) {
            return message.get();
        } else throw new NotFoundMessageException(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
