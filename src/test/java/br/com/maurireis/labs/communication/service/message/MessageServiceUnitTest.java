package br.com.magalu.labs.communication.service.message;


import br.com.maurireis.labs.communication.dataprovider.model.Destination;
import br.com.maurireis.labs.communication.dataprovider.model.Message;
import br.com.maurireis.labs.communication.dataprovider.model.MessageState;
import br.com.maurireis.labs.communication.dataprovider.model.MessageType;
import br.com.maurireis.labs.communication.dataprovider.repository.MessageRepository;
import br.com.maurireis.labs.communication.service.destination.DestinationService;
import br.com.maurireis.labs.communication.service.message.imp.MessageServiceImp;
import br.com.maurireis.labs.communication.service.rabbitmq.RabbitMqService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;


class MessageServiceUnitTest {

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
        given(messageRepository.save(any(Message.class)))
                .willReturn(getMockMessageSceneryNumberTwo());

        given(destinationService.create(any(String.class)))
                .willReturn(Optional.of(getMockMessageSceneryNumberTwo().getDestination()));

        final Message message = messageService.create(getMockMessageSceneryNumberOne());

        assertNotNull(message);
        assertEquals(ID, message.getId());
    }

    @Test
    void shouldSaveMessageAndReturnStateScheduled(){
        given(messageRepository.save(any(Message.class)))
                .willReturn(getMockMessageSceneryNumberTwo());

        given(destinationService.create(any(String.class)))
                .willReturn(Optional.of(getMockMessageSceneryNumberTwo().getDestination()));

        final Message message = messageService.create(getMockMessageSceneryNumberOne());

        assertNotNull(message);
        assertEquals(MessageState.SCHEDULED, message.getMessageState());
    }


    @Test
    void shouldDeleteMessageAndReturnMessageStateDeleted(){

        given(messageRepository.findById(ID))
                .willReturn(Optional.of(getMockMessageSceneryNumberOne()));

        given(messageRepository.save(getMockMessageSceneryNumberOne()))
                .willReturn(getMockMessageSceneryNumberOne());


        messageService.deleteById(ID);
        final ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);

        verify(this.messageRepository, times(1)).findById(ID);
        verify(this.messageRepository, times(1)).save(captor.capture());

        assertEquals(MessageState.DELETED, captor.getValue().getMessageState());

    }

    @Test
    void shouldFindByIdAndReturnMessage(){
        given(messageRepository.findById(anyLong()))
                .willReturn(Optional.of(getMockMessageSceneryNumberOne()));

        Message message = messageService.findById(ID);
        assertNotNull(message);
    }

    @Test
    void shouldFindAllAndReturnListMessage(){
        given(messageRepository.findAll())
                .willReturn(List.of(getMockMessageSceneryNumberOne()));

        List<Message> messages = messageService.findAll();
        assertNotNull(messages);
    }

    @Test
    void shouldFindByIdAndReturnException(){
        given(messageRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> messageService.findById(ID))
                .isInstanceOf(NotFoundMessageException.class);
    }


    @Test
    void shouldDeleteMessageAndReturnException(){

        given(messageRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());


        given(messageRepository.save(any(Message.class)))
                .willReturn(getMockMessageSceneryNumberOne());

        assertThatThrownBy(() -> messageService.deleteById(ID))
                .isInstanceOf(DelectedMessageFailException.class);
    }

    @Test
    void shouldCreateMessageAndReturnException(){

        given(messageRepository.save(any(Message.class)))
                .willReturn(getMockMessageSceneryNumberTwo());

        given(destinationService.create(any(String.class)))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> messageService.create(getMockMessageSceneryNumberOne()))
                .isInstanceOf(CreateMessageFailException.class);
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
