package com.rs.retailstore.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.rs.retailstore.model.Greeting;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
public class CustomerGreetingController {
	
	private static final String greetingTemplate = "Hello, %s %s";
	
	private final AtomicLong counter= new AtomicLong();

	@ApiOperation(value = "Get example data", notes = "Retrieve example data from the server.")
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value="gender", defaultValue = "0") boolean gender,
							@RequestParam(value = "CustomerName", defaultValue = "Customer") String customer) {
		return Greeting.builder()
				.id(counter.incrementAndGet())
				.content(String.format(greetingTemplate, gender?"Mr.":"Ms.",customer))
				.build();
	}
}
