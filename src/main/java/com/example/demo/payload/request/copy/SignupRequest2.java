package com.example.demo.payload.request.copy;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest2 {
  @NotBlank
  @Size(min = 3, max = 20)
  private String cin;
  @NotBlank
  @Size(min = 3, max = 20)
  private String delegation;



  @NotBlank
  @Size(min = 4, max = 40)
  private String password;

}
