package br.com.magalu.labs.communication.service.message;


import br.com.magalu.labs.communication.dataprovider.model.Destination;
import br.com.magalu.labs.communication.dataprovider.model.Message;
import br.com.magalu.labs.communication.dataprovider.model.MessageState;
import br.com.magalu.labs.communication.dataprovider.model.MessageType;
import br.com.magalu.labs.communication.dataprovider.repository.DestinationRepository;
import br.com.magalu.labs.communication.dataprovider.repository.MessageRepository;
import br.com.magalu.labs.communication.exception.CreateMessageFailException;
import br.com.magalu.labs.communication.exception.DelectedMessageFailException;
import br.com.magalu.labs.communication.exception.NotFoundMessageException;
import br.com.magalu.labs.communication.service.destination.DestinationService;
import br.com.magalu.labs.communication.service.rabbitmq.RabbitMqService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
class MessageServiceIntegrationTest {

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
    void shouldSaveMessageAndReturnIdAndStateScheduled(){
        final Message message = messageService.create(getMockMessageSceneryNumberOneDateTimeScheduleFuture());
        assertNotNull(message);
        assertEquals(1L, message.getId());
        assertEquals(MessageState.SCHEDULED, message.getMessageState());
    }

    @Test
    void shouldDeleteMessageAndReturnMessageStateDeleted(){
        final Message messageSave = messageService.create(getMockMessageSceneryNumberOneDateTimeScheduleFuture());
        messageService.deleteById(messageSave.getId());
        final Message message = messageService.findById(messageSave.getId());
        assertEquals(MessageState.DELETED, message.getMessageState());
    }

    @Test
    void whenValidatePastMessageToPersistDateTimeSchedulePassedReturnException(){
        assertThatThrownBy(() ->  messageService.create(getMockMessageSceneryNumberTwoDateTimeSchedulePassed()))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("Data do agendamento da mensagem e inválida");
    }

    @Test
    void shouldDeleteMessageAndReturnException(){
        assertThatThrownBy(() ->  messageService.deleteById(6L))
                .isInstanceOf(DelectedMessageFailException.class)
                .hasMessageContaining("Falha ao deletar a mensagem de id: 6");
    }

    @Test
    void shouldMessageFindByIdAndReturnException(){
        assertThatThrownBy(() ->  messageService.findById(6L))
                .isInstanceOf(NotFoundMessageException.class)
                .hasMessageContaining("Menssagem nao localizada pelo id: 6");
    }

    @Test
    void shouldMessageFindAllReturnList(){
        List<Message> list = messageService.findAll();
        assertNotNull(list);
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
                .id(null)
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
