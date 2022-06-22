package com.example.demo.controller;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Candidat;
import com.example.demo.payload.request.copy.LoginRequest2;
import com.example.demo.payload.request.copy.SignupRequest2;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.payload.response.copy.JwtResponse2;
import com.example.demo.repository.CandidatRepository;
import com.example.demo.security.jwt2.JwtUtils2;
import com.example.demo.security.services.candidat.UserDetailsImplC;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("admin/auth")
public class AuthController {
	@Autowired 
	CandidatRepository userRepository;

	 @Autowired
	  AuthenticationManager authenticationManager;


	  @Autowired
	  PasswordEncoder encoder;


	  @Autowired
	  JwtUtils2 jwtUtils2;


	  @PostMapping("/signin/candidat")
	  public ResponseEntity<?> authenticateCandidat(@Valid @RequestBody LoginRequest2 loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getCin(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils2.generateJwtToken(authentication);
	    
	    UserDetailsImplC userDetails = (UserDetailsImplC) authentication.getPrincipal();    

	    return ResponseEntity.ok(new JwtResponse2(
	    		jwt,
	    		userDetails.getcin(),
	    		userDetails.getDelegation()
	    		));

	  }
	  @PostMapping("/signup/candidat")
	  public ResponseEntity<?> registerCandidat(@Valid @RequestBody SignupRequest2 signUpRequest) {
	    if (userRepository.existsById(signUpRequest.getCin())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: CIN is already taken!"));
	    }

	    // Create new user's account
	    Candidat user = new Candidat(signUpRequest.getCin(),
	    		signUpRequest.getDelegation(),
	            encoder.encode(signUpRequest.getPassword())
	            		   );

	   


	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("Candidat registered successfully!"));
	  }

}
