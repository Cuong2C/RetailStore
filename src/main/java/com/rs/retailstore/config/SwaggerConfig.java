package com.rs.retailstore.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket createApiDocket() {
		return new Docket(DocumentationType.OAS_30).apiInfo(getApiInfor())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rs.retailstore.controller"))
				.paths(PathSelectors.any())
				.build()
				.protocols(Set.of("http","https"));
	}

	private ApiInfo getApiInfor() {
		
		return new ApiInfoBuilder()
				.title("Retail Store Application")
				.description("Retail Store description")
				.version("1.0")
				.build();
	}
}
