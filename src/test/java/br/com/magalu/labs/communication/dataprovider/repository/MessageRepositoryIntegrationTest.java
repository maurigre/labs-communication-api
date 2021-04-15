package br.com.magalu.labs.communication.dataprovider.repository;

import br.com.magalu.labs.communication.dataprovider.model.Destination;
import br.com.magalu.labs.communication.dataprovider.model.Message;
import br.com.magalu.labs.communication.dataprovider.model.MessageState;
import br.com.magalu.labs.communication.dataprovider.model.MessageType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class MessageRepositoryIntegrationTest{

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    DestinationRepository destinationRepository;

    Destination destination;
    Destination destination2;

    @AfterAll
    void tearDown() {
        messageRepository.deleteAll();
    }

    @Test
    @Order(1)
    void shouldSaveMessageAndReturnById(){
        Message save = messageRepository.save(getMockMessageSceneryNumberOne());
        assertEquals(2L, save.getId());
    }

    @Test
    @Order(2)
    void whenValidatePastMessageToPersistRetroactiveDateTimeReturnException (){
        assertThatThrownBy(() ->  messageRepository.save(getMockMessageSceneryNumberTwo()))
                .isInstanceOf(ConstraintViolationException.class);
    }

    private Message getMockMessageSceneryNumberOne(){
        final Destination destination = Destination.builder()
                .id(null)
                .destiny("teste@test.com.br")
                .build();

        final Destination destinationSaved = destinationRepository.save(destination);

        Message message = Message.builder()
                .id(null)
                .destination(destinationSaved)
                .dateTimeSchedule(LocalDateTime.now().plusMinutes(30))
                .message("Teste de mensagem")
                .messageType(MessageType.EMAIL)
                .messageState(MessageState.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();

        return message;

    }
    private Message getMockMessageSceneryNumberTwo(){
        final Destination destination = Destination.builder()
                .id(null)
                .destiny("teste@test.com.br")
                .build();

        final Destination destinationSaved = destinationRepository.save(destination);

        Message message = Message.builder()
                .id(null)
                .destination(destinationSaved)
                .dateTimeSchedule(LocalDateTime.now().minusMinutes(30))
                .message("Teste de mensagem")
                .messageType(MessageType.EMAIL)
                .messageState(MessageState.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();

        return message;

    }

}
