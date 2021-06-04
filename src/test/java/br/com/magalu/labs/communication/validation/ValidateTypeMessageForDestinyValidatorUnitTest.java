package br.com.magalu.labs.communication.validation;

import br.com.magalu.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.magalu.labs.communication.dataprovider.model.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class ValidateTypeMessageForDestinyValidatorUnitTest {

    final String DESTINY = "16997754335";
    final String MESSAGE ="Test object MessageDto";
    final String TYPE ="SMS";


    ValidateTypeMessageForDestinyValidator validateTypeMessageForDestinyValidator;
    ValidateTypeMessageForDestiny validateTypeMessageForDestiny;
    LocalValidatorFactoryBean validator;

    @BeforeEach
    void setUp() {
        validateTypeMessageForDestinyValidator = spy(ValidateTypeMessageForDestinyValidator.class);
        validateTypeMessageForDestiny = mock(ValidateTypeMessageForDestiny.class);
        validator = spy(LocalValidatorFactoryBean.class);
        validator.afterPropertiesSet();
    }
    @Test
    void shouldValidateTypeVsEmailReturnException(){
        assertThatThrownBy(() -> validateTypeMessageForDestinyValidator.isValid(any(), any()))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void shouldValidateTheMessageTypeAndDestinationAndReturnTrueForSmsType(){

        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("16999999999");
        mockMessageDto.setType(MessageType.SMS.name());

        Set<ConstraintViolation<MessageDto>> violations =
                validator.validate(mockMessageDto);

        assertEquals(true, violations.isEmpty());
    }

    @Test
    void shouldValidateTheMessageTypeAndDestinationAndReturnTrueForWhatsappType(){

        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("16999999999");
        mockMessageDto.setType(MessageType.WHATSAPP.name());

        Set<ConstraintViolation<MessageDto>> violations =
                validator.validate(mockMessageDto);

        assertEquals(true, violations.isEmpty());
    }

    @Test
    void shouldValidateTheMessageTypeAndDestinationAndReturnTrueForPushType(){

        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("16999999999");
        mockMessageDto.setType(MessageType.PUSH.name());

        Set<ConstraintViolation<MessageDto>> violations =
                validator.validate(mockMessageDto);

        assertEquals(true, violations.isEmpty());
    }

    @Test
    void shouldValidateTheMessageTypeAndDestinationAndReturnTrueForEmailType(){

        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("teste@test.com.br");
        mockMessageDto.setType(MessageType.EMAIL.name());

        Set<ConstraintViolation<MessageDto>> violations =
                validator.validate(mockMessageDto);

        assertEquals(true, violations.isEmpty());
    }

    @Test
    void shouldValidateTheMessageTypeAndDestinationAndReturnFalseForSmsType(){

        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("teste@test.com.br");
        mockMessageDto.setType(MessageType.SMS.name());

        Set<ConstraintViolation<MessageDto>> violations =
                validator.validate(mockMessageDto);

        ConstraintViolation<MessageDto> violation = violations.iterator().next();

        assertEquals(false, violations.isEmpty());
        assertEquals("{TypeMessageForDestiny.divergent}",
                violation.getMessageTemplate());
    }

    @Test
    void shouldValidateTheMessageTypeAndDestinationAndReturnFalseForWhatsappType(){

        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("teste@test.com.br");
        mockMessageDto.setType(MessageType.WHATSAPP.name());

        Set<ConstraintViolation<MessageDto>> violations =
                validator.validate(mockMessageDto);

        ConstraintViolation<MessageDto> violation = violations.iterator().next();

        assertEquals(false, violations.isEmpty());
        assertEquals("{TypeMessageForDestiny.divergent}",
                violation.getMessageTemplate());
    }

    @Test
    void shouldValidateTheMessageTypeAndDestinationAndReturnFalseForPushType(){

        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("teste@test.com.br");
        mockMessageDto.setType(MessageType.PUSH.name());

        Set<ConstraintViolation<MessageDto>> violations =
                validator.validate(mockMessageDto);

        ConstraintViolation<MessageDto> violation = violations.iterator().next();

        assertEquals(false, violations.isEmpty());
        assertEquals("{TypeMessageForDestiny.divergent}",
                violation.getMessageTemplate());
    }

    @Test
    void shouldValidateTheMessageTypeAndDestinationAndReturnFalseForEmailType(){

        MessageDto mockMessageDto = getMockMessageDto();
        mockMessageDto.setDestiny("16999999999");
        mockMessageDto.setType(MessageType.EMAIL.name());

        Set<ConstraintViolation<MessageDto>> violations =
                validator.validate(mockMessageDto);

        ConstraintViolation<MessageDto> violation = violations.iterator().next();

        assertEquals(false, violations.isEmpty());
        assertEquals("{TypeMessageForDestiny.divergent}",
                violation.getMessageTemplate());
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
