package br.com.magalu.labs.communication.service.message;


import br.com.magalu.labs.communication.dataprovider.model.Destination;
import br.com.magalu.labs.communication.dataprovider.model.Message;
import br.com.magalu.labs.communication.dataprovider.model.MessageState;
import br.com.magalu.labs.communication.dataprovider.model.MessageType;
import br.com.magalu.labs.communication.dataprovider.repository.MessageRepository;
import br.com.magalu.labs.communication.service.destination.DestinationService;
import br.com.magalu.labs.communication.service.message.imp.MessageServiceImp;
import br.com.magalu.labs.communication.service.rabbitmq.RabbitMqService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class MessageServiceUnitTest {

    MessageService messageService;
    DestinationService destinationService;
    MessageRepository messageRepository;
    RabbitMqService rabbitMqService;

    final Long ID = 2L;

    @BeforeEach
    void setUp() {
        this.messageRepository = spy(MessageRepository.class);
        this.destinationService = mock(DestinationService.class);
        this.rabbitMqService = mock(RabbitMqService.class);
        this.messageService = new MessageServiceImp(this.messageRepository, this.destinationService, this.rabbitMqService);
    }

    @Test
    void shouldSaveMessageAndReturnId(){
        BDDMockito.given(messageRepository.save(any(Message.class)))
                .willReturn(getMockMessageSceneryNumberTwo());

        BDDMockito.given(destinationService.create(any(String.class)))
                .willReturn(Optional.of(getMockMessageSceneryNumberTwo().getDestination()));

        final Message message = messageService.create(getMockMessageSceneryNumberOne());

        assertNotNull(message);
        assertEquals(ID, message.getId());
    }

    @Test
    void shouldSaveMessageAndReturnStateScheduled(){
        BDDMockito.given(messageRepository.save(any(Message.class)))
                .willReturn(getMockMessageSceneryNumberTwo());

        BDDMockito.given(destinationService.create(any(String.class)))
                .willReturn(Optional.of(getMockMessageSceneryNumberTwo().getDestination()));

        final Message message = messageService.create(getMockMessageSceneryNumberOne());

        assertNotNull(message);
        assertEquals(MessageState.SCHEDULED, message.getMessageState());
    }


    @Test
    void shouldDeleteMessageAndReturnMessageStateDeleted(){

        BDDMockito.given(messageRepository.findById(ID))
                .willReturn(Optional.of(getMockMessageSceneryNumberOne()));

        BDDMockito.given(messageRepository.save(getMockMessageSceneryNumberOne()))
                .willReturn(getMockMessageSceneryNumberOne());


        messageService.deleteById(ID);
        final ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);

        verify(this.messageRepository, times(1)).findById(ID);
        verify(this.messageRepository, times(1)).save(captor.capture());

        assertEquals(MessageState.DELETED, captor.getValue().getMessageState());

    }

    public Message getMockMessageSceneryNumberOne(){
        Destination destination = Destination.builder()
                .id(1L)
                .destiny("teste@test.com.br")
                .build();

        Message message = Message.builder()
                .id(ID)
                .destination(destination)
                .dateTimeSchedule(LocalDateTime.of(2021,06,01,10,00,50))
                .message("Teste de mensagem")
                .messageType(MessageType.EMAIL)
                .messageState(MessageState.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();

        return message;

    }

    public Message getMockMessageSceneryNumberTwo(){
        Destination destination = Destination.builder()
                .id(1L)
                .destiny("teste@test.com.br")
                .build();

        Message message = Message.builder()
                .id(2L)
                .destination(destination)
                .dateTimeSchedule(LocalDateTime.of(2021,06,01,10,00,50))
                .message("Teste de mensagem")
                .messageType(MessageType.EMAIL)
                .messageState(MessageState.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();

        return message;

    }

}
