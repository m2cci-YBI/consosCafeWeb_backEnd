package com.example.demo.auth.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.auth.JwtUtil.JwtUtil;
import com.example.demo.auth.model.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
/*Filtre JWT de l'authentification,Son objectif de verifier que le user existe et ensuite 
 * lui cree un token*/
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	JwtUtil jwtUtil = new JwtUtil();

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		AuthenticationRequest authenticationRequest;

		Authentication authenticationAv;
		Authentication authenticationAp;

		try {
			// Etape1 :on recupere le username et mot de passe dans l'object
			// authenticationRequest
			authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// Etape2: on fournie le username et password 
		authenticationAv = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

		// Etape 3:on demande a authenticationManager tente de s'identifier.
		authenticationAp = authenticationManager.authenticate(authenticationAv);

		// Etape 4 : on retourne l'objet Authentication,
		return authenticationAp;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		// Si Authentication reussite, on construie le token et on
		// le met dans le header de la reponse

		response.addHeader("Authorization", "Bearer " + jwtUtil.createToken(authResult));

	}

}
