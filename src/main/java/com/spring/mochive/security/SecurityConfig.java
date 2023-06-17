package com.spring.mochive.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http
		.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		.csrf().ignoringAntMatchers("/h2-console/**")
		.and()
		.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		;

		return http.build();
		
	}
	
}