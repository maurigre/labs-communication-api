package br.com.maurireis.labs.communication.service.rabbitmq.imp;


import br.com.maurireis.labs.communication.service.rabbitmq.RabbitMqService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RabbitMqServiceImp implements RabbitMqService {


    private final RabbitTemplate rabbitTemplate;

    @Override
    public void producer(Object message) {
        try {
            rabbitTemplate.convertAndSend(message);
        } catch (RuntimeException ex){

       }
    }
}
