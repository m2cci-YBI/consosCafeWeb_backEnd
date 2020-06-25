package com.example.demo.auth.JwtUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

	private String SECRET_KEY="dhidushdsihvdnvduhdfgvnbd;vbgo;gdvohdgiodH;OGHdhbgdbdho";

	public JwtUtil() {
		super();
	}
	
	
	public Jws<Claims> validateClaims(String token) {
		return Jwts.parser()
			  .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
			  .parseClaimsJws(token);			  	
	}
		
	public String createToken(Authentication authResult) {
		return Jwts.builder()
				.setSubject(authResult.getName())
				.claim("authorities",authResult.getAuthorities())				
				.setIssuedAt(new Date())
	            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
	            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
	            .compact();
	}
	
	
	
	
}
