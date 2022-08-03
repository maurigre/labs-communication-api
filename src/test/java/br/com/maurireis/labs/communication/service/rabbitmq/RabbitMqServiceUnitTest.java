package br.com.maurireis.labs.communication.service.rabbitmq;

import br.com.maurireis.labs.communication.service.rabbitmq.imp.RabbitMqServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RabbitMqServiceUnitTest {

    RabbitTemplate rabbitTemplate;
    RabbitMqService rabbitMqService;

    @BeforeEach
    void setUp() {
        this.rabbitTemplate = mock(RabbitTemplate.class);
        this.rabbitMqService = new RabbitMqServiceImp(this.rabbitTemplate);
    }

    @Test
    void shouldSendMessageForProducerRabbitMqVoidMethodAndThrowException(){
        doThrow(new RuntimeException())
                .when(rabbitTemplate).convertAndSend(any());
        rabbitMqService.producer(any());

    }

    @Test
    void shouldSendMessageForProducerRabbitMqVoidMethodAndNotThrowException(){
        rabbitMqService.producer(any());
        verify(rabbitTemplate).convertAndSend(any());
    }


}
