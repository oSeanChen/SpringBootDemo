package com.example.demo.controller;

import com.example.demo.MybatisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.MybatisTest;

import java.util.List;


@Controller
public class WelcomeController {

	@Autowired
	MybatisTestService mybatisTestService;
	
	@GetMapping("/welcome")
	public String welcome(Model model, 			
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "phone", required = false) String phone, 
			@RequestParam(value = "email", required = false) String email) {
		
		MybatisTest mybatisTest = new MybatisTest();
		
		mybatisTest.setName(name);
		mybatisTest.setPhone(phone);
		mybatisTest.setEmail(email);

		List<MybatisTest> mybatisTestList = mybatisTestService.queryAll("", "", "");
		
		model.addAttribute("mybatisTest", mybatisTest);
		model.addAttribute("mybatisTestList", mybatisTestList);
		return "welcome";
	}

}
