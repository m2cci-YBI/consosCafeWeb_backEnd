package com.example.demo.auth.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.auth.JwtUtil.JwtUtil;
import com.example.demo.auth.model.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
     
	
	
	//probleme de autowire a resoudre
	private JwtUtil jwtUtil =new JwtUtil();
	
	private final AuthenticationManager authenticationManager;
	

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		AuthenticationRequest authenticationRequest;

		Authentication authentication;

		try {
			authenticationRequest = new ObjectMapper()// Etape1 :on recupere le username et mot de passe dans l'object
														// authenticationRequest
					.readValue(request.getInputStream(), AuthenticationRequest.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		authentication = authenticationManager.authenticate(// Etape2: on tente de s'identifier avec le username et le
															// mot de passe
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		return authentication;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		  
		
		//Authentication reussite, donc on construie le token et on
		//le met dans le header de la reponse
		
		
		response.addHeader("Authorization", "Bearer " + jwtUtil.createToken(authResult));

	}

}
