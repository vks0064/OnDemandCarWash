package com.ondemandcarwash.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TestController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, from customer";
	}

}



