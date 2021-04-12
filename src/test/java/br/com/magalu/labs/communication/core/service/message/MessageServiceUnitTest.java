package br.com.magalu.labs.communication.core.service.message;


import br.com.magalu.labs.communication.core.model.Destination;
import br.com.magalu.labs.communication.core.model.Message;
import br.com.magalu.labs.communication.core.model.MessageState;
import br.com.magalu.labs.communication.core.model.MessageType;
import br.com.magalu.labs.communication.core.repository.MessageRepository;
import br.com.magalu.labs.communication.core.service.destination.DestinationService;
import br.com.magalu.labs.communication.core.service.message.imp.MessageServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class MessageServiceUnitTest {

    MessageService messageService;
    DestinationService destinationService;
    MessageRepository messageRepository;

    final Long ID = 2L;

    @BeforeEach
    void setUp() {
        this.messageRepository = spy(MessageRepository.class);
        this.destinationService = mock(DestinationService.class);
        this.messageService = new MessageServiceImp(this.messageRepository, this.destinationService);
    }

    @Test
    void shouldSaveMessageAndReturnId(){
        BDDMockito.given(messageRepository.save(Mockito.any(Message.class)))
                .willReturn(getMockMessage2());

        BDDMockito.given(destinationService.save(Mockito.any(String.class)))
                .willReturn(Optional.of(getMockMessage2().getDestination()));

        Optional<Message> message = messageService.save(getMockMessage1());

        assertTrue(message.isPresent());
        assertEquals(ID, message.get().getId());
    }


    @Test
    void shouldDeleteMessageAndReturnMessageStateDeleted(){

        BDDMockito.given(messageRepository.findById(ID))
                .willReturn(Optional.of(getMockMessage1()));

        BDDMockito.given(messageRepository.save(getMockMessage1()))
                .willReturn(getMockMessage1());


        final Message message = messageService.deleteById(ID).get();
        final ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);

        verify(this.messageRepository, times(1)).findById(ID);
        verify(this.messageRepository, times(1)).save(captor.capture());

        assertEquals(MessageState.DELETED, captor.getValue().getMessageState());

    }



    public Message getMockMessage1(){
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

    public Message getMockMessage2(){
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
