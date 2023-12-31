package com.rs.retailstore.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.repository.CustomerRepository;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName(); // lấy username được gởi lên qua authentication
		String password = authentication.getCredentials().toString();//tương tự trên lấy password dạng object và chuyển về String
		List<Customer> customers = customerRepository.findByUsername(username);
		if(CollectionUtils.isEmpty(customers)) {
			throw new BadCredentialsException("No customer registed with this username: "+ username);
		}else {
			if(passwordEncoder.matches(password, customers.get(0).getPassword())){
				List<GrantedAuthority> authorities = new ArrayList<>();	
				authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(username, password, authorities);
				}else {
					throw new BadCredentialsException("Invalid password for username: "+username);
				}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
