package br.com.magalu.labs.communication.dataprovider.model;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MessageUnitTest {

    private final Long ID = 1L;
    private final LocalDateTime DATE_TIME_SHEDULE =
            LocalDateTime.of(2021,04,14,
                    17,44,00);
    private final Destination DESTINATION =
            new Destination(1L, "teste@teste.com.br");
    private final String MESSAGE = "Mensagem de teste";
    private final MessageType MESSAGE_TYPE = MessageType.EMAIL;
    private final MessageState MESSAGE_STATE = MessageState.SCHEDULED;
    private final LocalDateTime CREATED_AT =
            LocalDateTime.of(2021,04,14,
            17,44,00);

    private final LocalDateTime MODIFIED_AT =
            LocalDateTime.of(2021,04,15,
            18,22,01);


    @Test
    @Order(1)
    void shouldCreateMessageForBuild(){

        Message build = Message.builder()
                .id(ID)
                .dateTimeSchedule(DATE_TIME_SHEDULE)
                .destination(DESTINATION)
                .message(MESSAGE)
                .messageType(MESSAGE_TYPE)
                .messageState(MESSAGE_STATE)
                .createdAt(CREATED_AT)
                .modifiedAt(MODIFIED_AT)
                .build();

        assertEquals(ID, build.getId());
        assertEquals(DATE_TIME_SHEDULE, build.getDateTimeSchedule());
        assertEquals(DESTINATION, build.getDestination());
        assertEquals(MESSAGE, build.getMessage());
        assertEquals(MESSAGE_TYPE, build.getMessageType());
        assertEquals(MESSAGE_STATE, build.getMessageState());
        assertEquals(CREATED_AT, build.getCreatedAt());
        assertEquals(MODIFIED_AT, build.getModifiedAt());

    }

    @Test
    @Order(2)
    void shouldCreateMessageConstructorNotArguments(){
        Message message = new Message();
        assertNull(message.getId());
        assertNull(message.getDateTimeSchedule());
        assertNull(message.getDestination());
        assertNull(message.getMessage());
        assertNull(message.getMessageType());
        assertNull(message.getMessageState());
        assertNull(message.getCreatedAt());
        assertNull(message.getModifiedAt());

    }

    @Test
    @Order(3)
    void shouldCreateMessageForConstructor() {
        Message message = new Message(ID, DATE_TIME_SHEDULE, DESTINATION, MESSAGE,
                MESSAGE_TYPE,MESSAGE_STATE, CREATED_AT, MODIFIED_AT);

        assertEquals(ID, message.getId());
        assertEquals(DATE_TIME_SHEDULE, message.getDateTimeSchedule());
        assertEquals(DESTINATION, message.getDestination());
        assertEquals(MESSAGE, message.getMessage());
        assertEquals(MESSAGE_TYPE, message.getMessageType());
        assertEquals(MESSAGE_STATE, message.getMessageState());
        assertEquals(CREATED_AT, message.getCreatedAt());
        assertEquals(MODIFIED_AT, message.getModifiedAt());
    }

    @Test
    @Order(4)
    void shouldEqualsAndHashCodeMessage(){

        Message builder = Message.builder()
                .id(ID)
                .dateTimeSchedule(DATE_TIME_SHEDULE)
                .destination(DESTINATION)
                .message(MESSAGE)
                .messageType(MESSAGE_TYPE)
                .messageState(MESSAGE_STATE)
                .createdAt(CREATED_AT)
                .modifiedAt(MODIFIED_AT)
                .build();

        Message constructor = new Message(ID, DATE_TIME_SHEDULE, DESTINATION, MESSAGE,
                MESSAGE_TYPE,MESSAGE_STATE, CREATED_AT, MODIFIED_AT);

        assertTrue(builder.equals(constructor));
    }

    @Test
    @Order(5)
    void shouldDiffForEqualsAndHashCodeMessage(){
        Message builder = Message.builder()
                .id(ID)
                .dateTimeSchedule(DATE_TIME_SHEDULE)
                .destination(DESTINATION)
                .message(MESSAGE)
                .messageType(MESSAGE_TYPE)
                .messageState(MESSAGE_STATE)
                .createdAt(CREATED_AT)
                .modifiedAt(MODIFIED_AT)
                .build();

        Message constructor = new Message(2L, DATE_TIME_SHEDULE, DESTINATION, MESSAGE,
                MESSAGE_TYPE,MESSAGE_STATE, CREATED_AT, MODIFIED_AT);

        assertFalse(builder.equals(constructor));
    }


}
