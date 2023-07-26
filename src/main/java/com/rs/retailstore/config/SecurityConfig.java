package com.rs.retailstore.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//	public UserDetailsService user(DataSource dataSource) {
//		UserDetails user = User.builder()
//				.username("user2")
//				.password("$2a$10$1ONQu6y5cij4VDWreKLkmOjCXPPdT1mqJ0FBJARXNw09q81WX2sB2")
//				.roles("USER")
//				.build();
//		UserDetails admin = User.builder()
//				.username("admin2")
//				.password("$2a$10$oTYGto/ndwJoRxf9dtbodeGAkBEF6cEFdv0p3DZMrOIzAQhWWDpEW")
//				.roles("USER", "ADMIN")
//				.build();
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.createUser(user);
//		users.createUser(admin);
//		return users;
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				
				.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests((a) -> a.requestMatchers("v1/register","/v3/**", "/swagger-ui/**").permitAll()
				.requestMatchers(HttpMethod.DELETE).permitAll()
				.requestMatchers(HttpMethod.PUT).permitAll()
				.requestMatchers("v1/greeting").authenticated())
				.formLogin(form -> form.defaultSuccessUrl("/v1/greeting"))
				.httpBasic(Customizer.withDefaults());
				
		return httpSecurity.build();

	}

}
