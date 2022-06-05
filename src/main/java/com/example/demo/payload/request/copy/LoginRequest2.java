	package com.example.demo.payload.request.copy;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest2 {
	@NotBlank
  private String cin;

	@NotBlank
	private String password;

}
