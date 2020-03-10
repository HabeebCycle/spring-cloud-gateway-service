package com.habeebcycle.service1api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-1")
public class Service1Controller {

	@GetMapping("/greet")
	public String test() {
		return "Hello World! Welcome to API Gateway Example. From Service 1 API";
	}
}