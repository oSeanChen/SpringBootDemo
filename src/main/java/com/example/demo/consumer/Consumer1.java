package com.example.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer1 {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "simple")
    public void receiveMessage(String message) {
        logger.info("------ {} Receive message：" + message, getClass().getSimpleName());
    }
}

