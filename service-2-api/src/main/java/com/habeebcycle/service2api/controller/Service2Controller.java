package com.habeebcycle.service2api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-2")
public class Service2Controller {

	@GetMapping("/greet")
	public String test() {
		return "Hello World! Welcome to API Gateway Example. From Service 2 API";
	}
}