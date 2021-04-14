package br.com.magalu.labs.communication.controller.v1.dto.mapper;

import br.com.magalu.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.magalu.labs.communication.dataprovider.model.Destination;
import br.com.magalu.labs.communication.dataprovider.model.Message;
import br.com.magalu.labs.communication.dataprovider.model.MessageType;

public class MessageMapper {


    public static MessageDto messageToDto(Message message){
        return MessageDto.builder()
                .dateTime(message.getDateTimeSchedule())
                .destiny(message.getDestination().getDestiny())
                .message(message.getMessage())
                .state(message.getMessageState().name())
                .type(message.getMessageType().name())
                .build();
    }

    public static Message DtoToMessage(MessageDto messageDto){
        return Message.builder()
                .dateTimeSchedule(messageDto.getDateTime())
                .destination(Destination.builder().destiny(messageDto.getDestiny()).build())
                .message(messageDto.getMessage())
                .messageType(MessageType.valueOf(messageDto.getType()))
                .build();
    }

}
