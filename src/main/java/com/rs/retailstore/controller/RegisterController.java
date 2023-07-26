package com.rs.retailstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1")
public class RegisterController {
	
	@Autowired
	CustomerService customerService;
	
	@ApiOperation(value = "User Registration", notes = "Registers a new user with the system.")
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer ){
		ResponseEntity<String> response = null;
		try {
			Customer savedCustomer = customerService.createCustomer(customer);
			if(savedCustomer.getId()>0) {
				response = ResponseEntity.status(HttpStatus.CREATED).body("Customer "+customer.getUsername()+" is created successfully");
			}
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Exception occurred from server"+ e);
		}
		return response;
		
	}
	
	

}
