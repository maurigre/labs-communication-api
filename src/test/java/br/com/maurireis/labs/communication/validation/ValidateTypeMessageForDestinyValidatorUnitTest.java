package br.com.maurireis.labs.communication.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ValidationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class ValidateTypeMessageForDestinyValidatorUnitTest {

    ValidateTypeMessageForDestinyValidator validator;

    @BeforeEach
    void setUp() {
        validator = Mockito.spy(ValidateTypeMessageForDestinyValidator.class);
    }

    @Test
    void shouldValidateTypeVsEmailReturnException(){
        assertThatThrownBy(() -> validator.isValid(any(), any()))
                .isInstanceOf(ValidationException.class);
    }


}
