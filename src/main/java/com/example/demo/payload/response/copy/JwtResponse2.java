package com.example.demo.payload.response.copy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse2 {
  private String token;
  private String type = "Bearer";
  private String cin;
  private String delegation;

  public JwtResponse2(String accessToken, String cin,String delegation) {
	    this.token = accessToken;
	    this.cin = cin;
	    this.delegation = delegation;

	  }


}
