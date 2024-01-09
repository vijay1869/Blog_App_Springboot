package com.blog.BlogAppApis.security;
//has methods to perform token related operations


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.micrometer.observation.Observation;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {
    public static final long JWT_TOKEN_VALIDITY=5*60*60;//milli seconds?
    private String secret="JwtTokenKey";
    //retrive username from jwt token
    public String getUsernameFormToken(String token) {
       // Observation.CheckedFunction<Claims, String, Throwable> getSubject = Claims::getSubject;
        return getClaimFromToken(token, Claims::getSubject);
    }

    //get expiration date
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //conversion from string to token
    public <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    //for retrieving any token from token we need the secrete key
    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
//check whether it is expeied
    public boolean isTokenExpired(String token){
        final Date expiration=getClaimFromToken(token,Claims::getExpiration);
        return expiration.before(new Date());

    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String,Object> claims,String subject){
        return String.valueOf(Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*100)));
    }


    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFormToken( token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }





}
