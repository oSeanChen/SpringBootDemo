package com.example.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // simple, work
    @Bean
    public Queue simpleQueue() {
        return new Queue("simple");
    }
    //	 Fanout
    @Bean
    public Queue fanoutQueueA() {return new Queue("fanout.A");}
    @Bean
    public Queue fanoutQueueB() {return new Queue("fanout.B");}
    @Bean
    public Queue fanoutQueueC() {return new Queue("fanout.C");}
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingFanoutExchangeA(Queue fanoutQueueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueA).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutExchangeB(Queue fanoutQueueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueB).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutExchangeC(Queue fanoutQueueC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueC).to(fanoutExchange);
    }

    //Direct
    @Bean
    public Queue directQueueA() {
        return new Queue("direct.A");
    }
    @Bean
    public Queue directQueueB() {
        return new Queue("direct.B");
    }
    @Bean
    public DirectExchange dircetExchange() {
        return new DirectExchange("directExchange");
    }


    @Bean
    Binding bindingDirectExchangeA(Queue directQueueA, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueA).to(directExchange).with("directA");
    }
    @Bean
    Binding bindingDirectExchangeB(Queue directQueueB, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueB).to(directExchange).with("directB");
    }

}
