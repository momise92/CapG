package com.capg.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	private Blacklist blacklist;

	public String getJwtInfo(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwt).getBody();
		return claims.toString();
	}

	@SuppressWarnings("unchecked")
	public UsernamePasswordAuthenticationToken getAuthenticationFromJWT(String jwt) throws Exception {
		UsernamePasswordAuthenticationToken result = null;

		try {
			Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwt).getBody();

			if (blacklist.userInList(claims.getSubject()))
				throw new Exception("Blacklisted User");

			List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
			for (String role : (List<String>) claims.get("authorities")) {
				SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_" + role);
				roles.add(sga);
			}

			result = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, roles);

		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public String createJWT(String user, List<String> roles) {
		Long now = System.currentTimeMillis();
		String jwt = Jwts.builder().setSubject(user).claim("authorities", roles.toArray(new String[0]))
				.setIssuedAt(new Date(now)).setExpiration(new Date(now + expiration * 1000)) // ex une journée (86400
																								// secondes)
				// = (heures, minutes, secondes)
				// 24*60*60 *1000 (car l
				// expiration est en
				// millisecondes)
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

		return jwt;
	}

	public String getSecret() {
		return secret;
	}

	public Long getExpiration() {
		return expiration;
	}

}
