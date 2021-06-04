package br.com.magalu.labs.communication.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class DateTimeMessageValidatorUnitTest {

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

}
