package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Producer;

@RestController
public class RabbitMqController {

	@Autowired
	private Producer producer;

	@PostMapping("/send")
	public String send(@RequestBody String msg) {
		int count = 5;

		for (int i = 1; i <= count; i++) {
			producer.sendMessage(i + " " + msg);
		}

		return msg + " send " + count + " times success";
	}

	@PostMapping("/send/fanout")
	public String sendByFanout(@RequestBody String msg) {
		int count = 3;

		for (int i = 1; i <= count; i++) {
			producer.sendMessageByFanout(i + " " + msg);
		}

		return msg + " send fanout" + count + " times success";
	}
	
	@PostMapping("send/direct")
	public String sendByDirect(@RequestBody String msg) {
		int count = 1;

		for (int i = 1; i <= count; i++) {
			
			if (msg.equals("directA")) {
				producer.sendMessageByDirectA(msg);
			} else {
				producer.sendMessageByDirectB(msg);
			}
		}

		return msg + " send direct " + count + " times success";
		
	}
}
