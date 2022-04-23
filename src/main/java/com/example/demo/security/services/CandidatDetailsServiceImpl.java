package com.example.demo.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Candidat;
import com.example.demo.repository.CandidatRepository;
@Service
public class CandidatDetailsServiceImpl implements UserDetailsService {
	@Autowired
	CandidatRepository userRepository;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Candidat candidat = userRepository.findByCin(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with CIN: " + username));
		return CandidatDetailsImpl.build(candidat);
	}

}
