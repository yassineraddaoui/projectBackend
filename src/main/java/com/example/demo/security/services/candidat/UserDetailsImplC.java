package com.example.demo.security.services.candidat;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Candidat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImplC implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;


  private String cin;
  private String delegation;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImplC( String cin, String password,String delegation,
      Collection<? extends GrantedAuthority> authorities) {
    this.cin = cin;
    this.password = password;
    this.authorities = authorities;
    this.delegation= delegation;
  }

  public static UserDetailsImplC build(Candidat user) {
    List<GrantedAuthority> authorities = null;

    return new UserDetailsImplC(
        user.getCin(), 
        user.getPassword(),
        user.getDelegation(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public String getcin() {
	    return cin;
	  }
  public String getDelegation() {
	    return delegation;
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
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImplC user = (UserDetailsImplC) o;
    return Objects.equals(id, user.id);
  }

@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return cin;
}
}
