package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
	private String message;
	private String password;
	public MessageResponse(String message) {
	    this.message = message;
	  }

}
