package com.example.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerFanout {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @RabbitListener(queues = "fanout.A")
        public void receiveMessageA(String message) {
            logger.info("------ {} consumer A Receive message：" + message, getClass().getSimpleName());
        }

        @RabbitListener(queues = "fanout.B")
        public void receiveMessageB(String message) {
            logger.info("------ {} consumer B Receive message：" + message, getClass().getSimpleName());
        }

        @RabbitListener(queues = "fanout.C")
        public void receiveMessageC(String message) {
            logger.info("------ {} consumer C Receive message：" + message, getClass().getSimpleName());
        }

}
