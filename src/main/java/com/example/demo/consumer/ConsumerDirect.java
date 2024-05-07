package com.example.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDirect {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RabbitListener(queues = "direct.A")
	public void receiveMessageA(String message) {
		logger.info("------ {} consumerDirect  A Receive message：" + message, getClass().getSimpleName());
	}

	@RabbitListener(queues = "direct.B")
	public void receiveMessageB(String message) {
		logger.info("------ {} consumerDirect  B Receive message：" + message, getClass().getSimpleName());
	}

}
