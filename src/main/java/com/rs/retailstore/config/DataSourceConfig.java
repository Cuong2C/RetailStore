//package com.rs.retailstore.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DataSourceConfig {
//
//	@Bean
//	public DataSource dataSource() {
//		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//		dataSourceBuilder.url("jdbc:mysql://localhost:3306/loginproject?useSSL=False");
//		dataSourceBuilder.username("root");
//		dataSourceBuilder.password("Cuong23111994@");
//		return dataSourceBuilder.build();
//	}
//}
