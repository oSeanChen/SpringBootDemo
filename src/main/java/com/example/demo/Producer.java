package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sendMessage(String message) {
		rabbitTemplate.convertAndSend("simple", message);
		logger.info(message + "----- Producer send to simpleQueue ------");
	}
	
	public void sendMessageByFanout(String message) {
		rabbitTemplate.convertAndSend("fanoutExchange","", message);
		logger.info(message + "----- Producer send to allFanoutQueue ------");
	}
	
	public void sendMessageByDirectA(String message) {
		rabbitTemplate.convertAndSend("directExchange","directA", message);
		logger.info(message + "----- Producer send to DirectQueueA ------");
	}
	
	public void sendMessageByDirectB(String message) {
		rabbitTemplate.convertAndSend("directExchange","directB", message);
		logger.info(message + "----- Producer send to DirectQueueB ------");
	}
}
