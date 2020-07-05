package com.example.demo.auth.filters;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.auth.MyUserDetailsService;
import com.example.demo.auth.JwtUtil.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil = new JwtUtil();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");
        //on verifie si la partie front end nous a envoyé le token dans le header
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String jwt = authorizationHeader.substring(7);// recuperer le token sans le prefix

		try {
			Jws<Claims> claims = jwtUtil.validateClaims(jwt); // valider et decoder le token grace a la signature
			String username = claims.getBody().getSubject();// recuperer le username

			// Recuperer les roles de l'utilisateur
			List<Map<String, String>> Jwtauthorities = (List<Map<String, String>>) claims.getBody().get("authorities");

			Set<SimpleGrantedAuthority> authorities = Jwtauthorities.stream()
					.map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
			// Creation de l'object authentication et mise dans le contexte de Spring
			// Security
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (JwtException e) {
			throw new IllegalStateException(String.format("token can't be trusted"));
		}
		filterChain.doFilter(request, response);

	}
}
