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
/*Classe qui regroupe des methodes de manipulations du token*/
public class JwtUtil {

	private final String SECRET_KEY = "dhidushdsihvdnvduhdfgvnbd;vbgo;gdvohdgiodH;OGHdhbgdbdho";

     /*cette methode s'assure que la signature du token correspont au header et payload du token,
      * soit le token n'a pas subit de modification */
	public  Jws<Claims> validateClaims(String token) {
		return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).parseClaimsJws(token);
	}
    
	public String createToken(Authentication authResult) {
		
		UserDetails user=(UserDetails) authResult.getPrincipal();
		
		return Jwts.builder().setSubject(user.getUsername()).claim("authorities", user.getAuthorities())
				.setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).compact();
	}

}
