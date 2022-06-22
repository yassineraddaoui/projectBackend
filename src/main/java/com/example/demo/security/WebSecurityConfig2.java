package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.jwt2.AuthEntryPointJwt2;
import com.example.demo.security.jwt2.AuthTokenFilter2;
import com.example.demo.security.services.candidat.UserDetailsServiceImplC;
@Order(1)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
     securedEnabled = true,
     jsr250Enabled = true,
    prePostEnabled = true)

public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImplC userDetailsService;

  @Autowired
  private AuthEntryPointJwt2 unauthorizedHandler;
  @Bean
  public AuthTokenFilter2 authenticationJwtTokenFilter2() {
    return new AuthTokenFilter2();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests().antMatchers("/admin/auth/**").permitAll()
      .anyRequest().authenticated();

    http.addFilterBefore(authenticationJwtTokenFilter2(), UsernamePasswordAuthenticationFilter.class);
  }
}