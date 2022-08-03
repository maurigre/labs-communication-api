package br.com.maurireis.labs.communication.controller.v1.dto.message;

import br.com.maurireis.labs.communication.dataprovider.model.MessageState;
import br.com.maurireis.labs.communication.dataprovider.model.MessageType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MessageDtoTest {

   final LocalDateTime DATE_TIME = LocalDateTime.of(2021, 05, 31, 15, 23, 03);
   final String DESTINY = "1699999999";
   final String MESSAGE ="Test object MessageDto";
    final String TYPE ="SMS";
    final String STATE = MessageState.DELETED.name();

    @Test
    void shouldHashCode(){
        MessageDto messageDto = getMockMessageDto();
        assertEquals(1172967820, messageDto.hashCode());
    }

    @Test
    void shouldHashCodeAttributes(){
        MessageDto messageDto = MessageDto.builder().build();
        assertEquals(1244954382, messageDto.hashCode());

        messageDto.setDateTime(DATE_TIME);
        assertEquals(1235765124, messageDto.hashCode());

        messageDto.setDestiny(DESTINY);
        assertEquals(-1198304654, messageDto.hashCode());

        messageDto.setMessage(MESSAGE);
        assertEquals(1168118610, messageDto.hashCode());

        messageDto.setType(TYPE);
        assertEquals(1172967820, messageDto.hashCode());

        messageDto.setState(STATE);
        assertEquals(-853553830, messageDto.hashCode());
    }

    @Test
    void shouldToString(){
        StringBuilder messageDtoToString = new StringBuilder();
        messageDtoToString.append("MessageDto(dateTime=").append(DATE_TIME.toString()).append(", ");
        messageDtoToString.append("destiny=").append(DESTINY).append(", ");
        messageDtoToString.append("message=").append(MESSAGE).append(", ");
        messageDtoToString.append("type=").append(TYPE).append(", ");
        messageDtoToString.append("state=").append(STATE).append(")");

        MessageDto messageDto = new MessageDto(DATE_TIME, DESTINY, MESSAGE, TYPE, STATE);
        assertEquals(messageDtoToString.toString(), messageDto.toString());
    }

    @Test
    void shouldSetStateAndReturnEqualState(){
        MessageDto messageDto = MessageDto.builder().build();
        messageDto.setState(MessageState.SCHEDULED.name());
        assertEquals(MessageState.SCHEDULED.name(), messageDto.getState());
    }

    @Test
    void shouldSetDestinyAndReturnEqualDestiny(){
        String destiny = "1699999999";
        MessageDto messageDto = MessageDto.builder().build();
        messageDto.setDestiny(destiny);
        assertEquals(destiny, messageDto.getDestiny());
    }

    @Test
    void shouldSetMessageAndReturnEqualMessage(){
        String message = "Test object MessageDto";
        MessageDto messageDto = MessageDto.builder().build();
        messageDto.setMessage(message);
        assertEquals(message, messageDto.getMessage());
    }

    @Test
    void shouldSetTypeAndReturnEqualType(){
        String type = "SMS";
        MessageDto messageDto = MessageDto.builder().build();
        messageDto.setType(type);
        assertEquals(type, messageDto.getType());
    }

    @Test
    void shouldSetDateTimeAndReturnEqualDateTime(){
        LocalDateTime dateTime = LocalDateTime.of(2021, 05, 31, 15, 23, 03);
        MessageDto messageDto = MessageDto.builder().build();
        messageDto.setDateTime(dateTime);
        assertEquals(dateTime, messageDto.getDateTime());
    }

    @Test
    void shouldCompareAttributeStateInBetweenTwoMessageDtoAndReturnFalse(){
        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setState(MessageState.SCHEDULED.name());
        assertEquals(false, mockMessageDto.equals(getMockMessageDto()));
    }

    @Test
    void shouldCompareAttributeDestinyInBetweenTwoMessageDtoAndReturnFalse(){
        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("163333333333");
        assertEquals(false, mockMessageDto.equals(getMockMessageDto()));
    }

    @Test
    void shouldCompareTwoMessageDtoOneNullAndReturnFalse(){
        MessageDto mockMessageDto = getMockMessageDto();
        assertEquals(false, mockMessageDto.equals(null));
    }

    @Test
    void shouldCompareAttributeTypeInBetweenTwoMessageDtoAndReturnFalse(){
        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setType(MessageType.EMAIL.name());
        assertEquals(false, mockMessageDto.equals(getMockMessageDto()));
    }

    @Test
    void shouldCompareAttributeDateTimeInBetweenTwoMessageDtoAndReturnFalse(){
        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDateTime(LocalDateTime.now());
        assertEquals(false, mockMessageDto.equals(getMockMessageDto()));
    }
    @Test
    void shouldCompareAttributeMessageInBetweenTwoMessageDtoAndReturnFalse(){
        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setMessage("Test message diff");
        assertEquals(false, mockMessageDto.equals(getMockMessageDto()));
    }

    @Test
    void shouldCompareTwoMessageDtoAndReturnTrue(){
        MessageDto mockMessageDto = getMockMessageDto();
        assertEquals(true, mockMessageDto.equals(getMockMessageDto()));
        assertEquals(true, mockMessageDto.equals(mockMessageDto));
    }

    @Test
    void shouldCompareTwoObjectsAndReturnFalse(){
        MessageDto messageDtoTwo = MessageDto.builder().build();
        assertEquals(false, messageDtoTwo.equals(new String()));
        assertNotEquals(messageDtoTwo, getMockMessageDto());
    }

    @Test
    void shouldBuilderMessageDtoForToString(){
        String messageDtotoString = "MessageDto.MessageDtoBuilder(dateTime=null, destiny=null, message=null, type=null, state=null)";
        String string =  MessageDto.builder().toString();
        assertEquals(messageDtotoString, string);
    }

    private MessageDto getMockMessageDto(){
        return MessageDto.builder()
                .dateTime(DATE_TIME)
                .message(MESSAGE)
                .type(TYPE)
                .destiny(DESTINY)
                .build();
    }

}
