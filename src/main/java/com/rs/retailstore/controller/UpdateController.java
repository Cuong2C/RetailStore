package com.rs.retailstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1")
public class UpdateController {
	@Autowired
	CustomerService customerService;
	
	@ApiOperation(value = "Update User", notes = "Updates an existing user's information.")
    @PutMapping("/update/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
        ResponseEntity<String> response = null;
        try {
            Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
            if (updatedCustomer != null) {
                response = ResponseEntity.status(HttpStatus.OK)
                        .body("Customer " + customer.getUsername() + " is updated successfully");
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Customer with ID " + customerId + " not found");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Exception occurred from server" + e);
        }
        return response;
    }

}
