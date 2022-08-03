package br.com.maurireis.labs.communication.config.rabbitmq;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfiguration {

    @Getter
    @Value("${spring.rabbitmq.request.queue.producer}")
    private String queue;

    @Getter
    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String routing;

    @Getter
    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;

    @Getter
    @Value("${spring.rabbitmq.request.dead-letter.producer}")
    private String deadLetter;


    @Bean
    DirectExchange exchange(){
        return new DirectExchange(exchange);
    }

    @Bean
    Queue deadLetter(){
        return new Queue(deadLetter);
    }


    @Bean
    Queue queue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", exchange);
        args.put("x-dead-letter-routing-key", deadLetter);
        return new Queue(routing, true , false, false, args);
    }

    @Bean
    public Binding bindingQueue(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routing);
    }

    @Bean
    public Binding bindingDeadLetter(){
        return BindingBuilder.bind(deadLetter())
                .to(exchange())
                .with(deadLetter);
    }

}
