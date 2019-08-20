package com.retuerta.GamerStore.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.entities.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	final String SECRET_KEY = "12321"; 

	public String getToken(Usuario usuario) {
		String token = Jwts.builder().setIssuedAt(new Date())
				.setIssuer("GamerStore")
				.setSubject("Usuario")
				.claim("id", usuario.getId())
				.claim("email", usuario.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		
		return token;
	}
	

	public boolean validarToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return false;
		}
		
		if (claims == null || claims.isEmpty()) { return false; }
		return true;
	}

	
}
