package com.example.demo.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String cin;
	private String delegation;
	private List<String> roles;
	public JwtResponse(String accessToken, Long id, String cin, String delegation, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.cin = cin;
		this.delegation = delegation;
		this.roles = roles;
	}

}
