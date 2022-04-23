package com.example.demo.payload.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignUpRequest {

	 private String cin;
	 private String code;
	 private String delegation;

}
