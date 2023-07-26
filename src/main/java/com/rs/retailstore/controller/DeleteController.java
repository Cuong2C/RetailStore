package com.rs.retailstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.retailstore.service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1")
public class DeleteController {
	
	@Autowired
	CustomerService customerService;

	 @ApiOperation(value = "Delete User", notes = "Deletes an existing user.")
	    @DeleteMapping("/delete/{customerId}")
	    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
	        ResponseEntity<String> response = null;
	        try {
	            boolean deleted = customerService.deleteCustomer(customerId);
	            if (deleted) {
	                response = ResponseEntity.status(HttpStatus.OK)
	                        .body("Customer with ID " + customerId + " is deleted successfully");
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
