package com.rs.retailstore.model;

import lombok.Builder;
import lombok.Data;

@Data		// 
@Builder  // dùng để gán dữ liệu vào class Gretting
public class Greeting {
	private long id;
	private String content;

}
