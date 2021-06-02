package br.com.magalu.labs.communication.service.rabbitmq;

import br.com.magalu.labs.communication.service.rabbitmq.imp.RabbitMqServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.mock;

class RabbitMqServiceUnitTest {

    RabbitTemplate rabbitTemplate;
    RabbitMqService rabbitMqService;

    @BeforeEach
    void setUp() {
        this.rabbitTemplate = mock(RabbitTemplate.class);
        this.rabbitMqService = new RabbitMqServiceImp(this.rabbitTemplate);
    }

    @Test
    void shouldSendMessageForProducerRabbitMqAndReturnException(){

    }


}
