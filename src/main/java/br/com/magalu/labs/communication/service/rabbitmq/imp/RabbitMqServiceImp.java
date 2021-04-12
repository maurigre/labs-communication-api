package br.com.magalu.labs.communication.service.rabbitmq.imp;

import br.com.magalu.labs.communication.config.rabbitmq.ProducerConfiguration;
import br.com.magalu.labs.communication.service.rabbitmq.RabbitMqService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImp implements RabbitMqService {


    RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routing;

    @Autowired
    public RabbitMqServiceImp(RabbitTemplate rabbitTemplate, ProducerConfiguration producerConfiguration) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = producerConfiguration.getExchange();
        this.routing = producerConfiguration.getRouting();
    }

    @Override
    public void producer(Object message) {
        try {
            rabbitTemplate.convertAndSend(exchange, routing, message);
        } catch (Exception ex){
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
