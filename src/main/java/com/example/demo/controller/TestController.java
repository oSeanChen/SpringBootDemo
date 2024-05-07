package com.example.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.websocket.server.PathParam;

@RestController
public class TestController {
	@GetMapping("/test/{word}")
	public String test(@PathVariable String word) {
		
        if (word.equals("yes")) {
            throw new RuntimeException();
        }
		System.out.println("do test word = " + word);
		return word;
		
	}
	
	@PostMapping("/test1")
	public String test1(@RequestParam String name) {
		return name;
		
	}
	

}
