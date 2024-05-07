package com.example.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SSEController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/sse/messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamMessages() {
        return Flux.create(sink -> {
            // 监听 RabbitMQ 队列，接收消息并将其推送到前端
            rabbitTemplate.receive("your_queue_name", (message) -> {
                sink.next(ServerSentEvent.builder(message.getBody().toString()).build());
                return Mono.empty();
            });
        });
    }
}