package br.com.magalu.labs.communication.validation;

import br.com.magalu.labs.communication.controller.v1.dto.message.MessageDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DateTimeMessageValidatorUnitTest {

    final String DESTINY = "16997754335";
    final String MESSAGE ="Test object MessageDto";
    final String TYPE ="SMS";

    DateTimeMessageValidator dateTimeMessageValidator;

    @BeforeEach
    void setUp(){
        this.dateTimeMessageValidator = spy(DateTimeMessageValidator.class);
    }

    @Test
    void should(){
        final boolean valid = dateTimeMessageValidator.isValid(null, null);
        Assertions.assertEquals(true, valid);
    }

    @Test
    void shoulda(){
        LocalDateTime dateTimeMessage = LocalDateTime.now().minusSeconds(1) ;

        final boolean valid = dateTimeMessageValidator.isValid(dateTimeMessage, null);

        Assertions.assertEquals(false, valid);
    }
    @Test
    void shouldaSuccess(){
        LocalDateTime dateTimeMessage = LocalDateTime.now().plusSeconds(1);

        final boolean valid = dateTimeMessageValidator.isValid(dateTimeMessage, null);

        Assertions.assertEquals(true, valid);
    }


    private MessageDto getMockMessageDto(){
        return MessageDto.builder()
                .dateTime(LocalDateTime.now().plusMinutes(30))
                .message(MESSAGE)
                .type(TYPE)
                .destiny(DESTINY)
                .build();
    }

}
