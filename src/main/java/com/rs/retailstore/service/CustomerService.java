package com.rs.retailstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	public Customer createCustomer(Customer customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		return customerRepository.save(customer);
	}


	public Customer updateCustomer(int customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
        if (existingCustomer != null) {
 
            existingCustomer.setUsername(updatedCustomer.getUsername());
            existingCustomer.setPassword(passwordEncoder.encode(updatedCustomer.getPassword()));
            existingCustomer.setRole(updatedCustomer.getRole());
    
            return customerRepository.save(existingCustomer);
        }
        return null; 
    }


	public boolean deleteCustomer(int customerId) {
        Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
        if (existingCustomer != null) {
            customerRepository.delete(existingCustomer);
            return true;
        }
        return false; 
    }
	

}
