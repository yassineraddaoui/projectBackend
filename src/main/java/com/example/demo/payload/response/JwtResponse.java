package com.example.demo.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String matricule;
  private List<String> roles;

  public JwtResponse(String accessToken, String matricule, List<String> roles) {
	    this.token = accessToken;
	    this.matricule = matricule;
	    this.roles = roles;
	  }


}
