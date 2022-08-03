package br.com.maurireis.labs.communication.controller.v1.dto.mapper;

import br.com.maurireis.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.maurireis.labs.communication.dataprovider.model.Destination;
import br.com.maurireis.labs.communication.dataprovider.model.Message;
import br.com.maurireis.labs.communication.dataprovider.model.MessageState;
import br.com.maurireis.labs.communication.dataprovider.model.MessageType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageMapperUnitTest {


    @Test
    void shouldReceiveMessageToMessageDto(){
        MessageDto messageDto = MessageMapper.messageToDto(getMockMessage());
        assertEquals("teste@test.com.br", messageDto.getDestiny());
    }

    @Test
    void shouldReceiveMessageDtoToMessage(){
        Message message = MessageMapper.DtoToMessage(getMockMessageDto());
        assertEquals("169999999999", message.getDestination().getDestiny());
    }

    public Message getMockMessage(){
        Destination destination = Destination.builder()
                .id(1L)
                .destiny("teste@test.com.br")
                .build();

        Message message = Message.builder()
                .id(1L)
                .destination(destination)
                .dateTimeSchedule(LocalDateTime.of(2021,06,01,10,00,50))
                .message("Teste de mensagem")
                .messageType(MessageType.EMAIL)
                .messageState(MessageState.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();

        return message;
    }

    private MessageDto getMockMessageDto(){
         return MessageDto.builder()
                .dateTime(LocalDateTime.of(2021,06,01,10,00,50))
                .message("Teste objeto MessageDto")
                .type("SMS")
                .destiny("169999999999")
                .build();
    }

}
