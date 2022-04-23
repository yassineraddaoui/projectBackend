package com.example.demo.security.services;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Candidat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
@Data
public class CandidatDetailsImpl  implements UserDetails{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String cin;
	private String delegation;
	@JsonIgnore
	private String code;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public CandidatDetailsImpl(Long id, String cin, String delegation, String code,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.cin = cin;
		this.delegation = delegation;
		this.code = code;
		this.authorities = authorities;
	}

	
	public static CandidatDetailsImpl build(Candidat user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		return new CandidatDetailsImpl(
				user.getId(), 
				user.getCin(), 
				user.getDelegation(),
				user.getCode(), 
				authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return code;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return cin;
	}



}
