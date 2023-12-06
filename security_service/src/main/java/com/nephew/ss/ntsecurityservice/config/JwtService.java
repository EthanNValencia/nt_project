package com.nephew.ss.ntsecurityservice.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "4d4949424f41494241414a415369622f2b456445592b4d7869724c764e546a74354f6447417a4275676249314833756d73744562744b57546e3171306656636b0d0a5a594250517248467a6f413153627a51562b3039724257324669387030714b6d4c77494441514142416b41313376642f6247636e476c745870483963627975460d0a624f2f555355382f4d6a5364317274596c637048426d73785a742b354c4c65547a32386e46584546346b2b38644b385a46747547564274684b664937514b56680d0a416945416b5062682b5a375236436f787344695572434347416c4c624778354c416551625a567447763959414b54554349514343387777695677745a6d3436630d0a566d722f726c69323557546838315a31335a3279504b48504a6e4e69557749674334666e7a37424861423037474a5a586c5231394b32416c31354667544155310d0a2f4b6a3342726c69422b55434944504857784c784445486761714753346a6737666b544472414a554c2b6e6e4f595853783843625761435641694138424e506d0d0a70666d2f797655727a7733366662624862593837355830412b7061587545766e6174326c63673d3d";
	
	public String extractUsername(String jwt) {
		return extractClaim(jwt, Claims::getSubject);
	}
	
	// Generates without needing extraClaims provided. 
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	public String generateToken(Map<String, String> extraClaims, UserDetails userDetails) {
		// extraClaims.put("Test", "Test Entry");
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 12))) // Valid for 12 hours. 
				.signWith(getSignInKey(), SignatureAlgorithm.HS512)
				.compact();
	}
	
	public boolean isTokenValid(String jwt, UserDetails userDetails) {
		final String extractedUsername = extractUsername(jwt);
		return (extractedUsername.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
	}
	
	private boolean isTokenExpired(String jwt) {
		return extractExpiration(jwt).before(new Date());
	}

	private Date extractExpiration(String jwt) {
		return extractClaim(jwt, Claims::getExpiration);
	}

	public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(jwt);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String jwt) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
