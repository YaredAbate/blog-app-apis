/*package com.codewithyad.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenHelper {
	public static final long JWT_TOKEN_VALIDITY=5*60*60;
	private static String secret="jwtTokenKey";
	//Retrieve username from Jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	//Retrieve expiration data from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	} 
	public <T> T getClaimFromToken(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	//for retrievening any information from token we will need secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	//generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	//while creating the token
	//1. define claims of the token,like Issuer,Expiration,Subject,and the id
	//2. sign the jwt using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-j
	// compaction of the Jwt to aURL-safe String
	private static String doGenerateToken(Map<String, Object> claims, String subject) {
		  final Date createdDate = new Date();
		  final Date expirationDate = new Date(createdDate.getTime() + JWT_TOKEN_VALIDITY * 1000);
		  return Jwts.builder()
		      .setClaims(claims)
		      .setSubject(subject)
		      .setIssuedAt(createdDate)
		      .setExpiration(expirationDate)
		      .signWith(SignatureAlgorithm.HS512,secret)
		      .compact();
		}
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}*/
