package com.example.demo.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Generator;
import com.example.demo.model.Candidat;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignUpRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.CandidatRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.CandidatDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	CandidatRepository candidatRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getCin(), loginRequest.getCode()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		CandidatDetailsImpl userDetails = (CandidatDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getCin(), 
												 userDetails.getDelegation(), 
												 roles));
	}
	@PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpRequest signUpRequest) {
		System.out.println(signUpRequest.getCin());
	    if (candidatRepository.existsByCin(signUpRequest.getCin())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: CIN is already in use!"));
	    }
	    String x= Generator.generateRandomPassword(6);
	    // Create new user's account
	    Candidat user = new Candidat(signUpRequest.getCin(), 
	               signUpRequest.getDelegation(),encoder.encode(x));

//	    Set<String> strRoles = signUpRequest.getRole();
//	    Set<Role> roles = new HashSet<>();
//
//	    if (strRoles == null) {
//	      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	      roles.add(userRole);
//	    } else {
//	      strRoles.forEach(role -> {
//	        switch (role) {
//	        case "admin":
//	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	          roles.add(adminRole);
//
//	          break;
//	        default:
//	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	          roles.add(userRole);
//	        }
//	      });
//	    }
//
//	    user.setRoles(roles);
	    candidatRepository.save(user);
	    return ResponseEntity.ok().body(new MessageResponse(x));
	  }
}
