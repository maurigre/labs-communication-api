package br.com.magalu.labs.communication.service.message;


import br.com.magalu.labs.communication.dataprovider.model.Destination;
import br.com.magalu.labs.communication.dataprovider.model.Message;
import br.com.magalu.labs.communication.dataprovider.model.MessageState;
import br.com.magalu.labs.communication.dataprovider.model.MessageType;
import br.com.magalu.labs.communication.dataprovider.repository.MessageRepository;
import br.com.magalu.labs.communication.service.destination.DestinationService;
import br.com.magalu.labs.communication.service.rabbitmq.RabbitMqService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class MessageServiceIntegrationTest {

    @Autowired
    MessageService messageService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    DestinationService destinationService;

    @MockBean
    RabbitMqService rabbitMqService;

    @AfterAll
    void tearDown() {
        messageRepository.deleteAll();
    }

    @Test
    @Order(1)
    void shouldSaveMessageAndReturnIdAndStateScheduled(){
        final Message message = messageService.create(getMockMessageSceneryNumberOneDateTimeScheduleFuture());
        assertNotNull(message);
        assertEquals(1L, message.getId());
        assertEquals(MessageState.SCHEDULED, message.getMessageState());
    }

    @Test
    @Order(2)
    void shouldDeleteMessageAndReturnMessageStateDeleted(){
        messageService.deleteById(1L);
        final Message message = messageService.findById(1l);
        assertEquals(MessageState.DELETED, message.getMessageState());
    }

    @Test
    @Order(3)
    void whenValidatePastMessageToPersistDateTimeSchedulePassedReturnException(){
        assertThatThrownBy(() ->  messageService.create(getMockMessageSceneryNumberTwoDateTimeSchedulePassed()))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("Data do agendamento da mensagem e inválida");
    }

    public Message getMockMessageSceneryNumberOneDateTimeScheduleFuture(){

        Destination destination = Destination.builder()
                .id(1L)
                .destiny("teste@test.com.br")
                .build();

        Message message = Message.builder()
                .id(1l)
                .destination(destination)
                .dateTimeSchedule(LocalDateTime.now().plusDays(1).plusMinutes(30))
                .message("Teste de mensagem")
                .messageType(MessageType.EMAIL)
                .messageState(MessageState.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();

        return message;

    }

    public Message getMockMessageSceneryNumberTwoDateTimeSchedulePassed(){
        Destination destination = Destination.builder()
                .id(2L)
                .destiny("teste@test.com.br")
                .build();

        Message message = Message.builder()
                .id(2L)
                .destination(destination)
                .dateTimeSchedule(LocalDateTime.now().minusMinutes(30))
                .message("Teste de mensagem")
                .messageType(MessageType.EMAIL)
                .messageState(MessageState.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();

        return message;

    }

}
