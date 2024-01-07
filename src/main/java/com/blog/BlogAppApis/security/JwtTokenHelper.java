package com.blog.BlogAppApis.security;
//has methods to perform token related operations


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
//    public String getUsernameFromToken(String token){
//
//
//    }
    public String getUsernameFormToken(Token token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

//    private <T> String getClaimFromToken(String token, Function<Claims,T> getSubject) {
//    }

    //get expiration date
    public Date getExpirationDateFromToken(Token token){
        return getClaimFromToken(token, Claims::getExpiration);
    }
    //conversion from string to token
    public <T> T getClaimFromToken(Token token, Function<Claims,T> claimResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    //for retriveing any token from token we need the secrete key
    public Claims getAllClaimsFromToken(Token token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
//check whether it is expeied
    public boolean isTokenExpired(Token token){
        final Date expiration=getClaimFromToken(token,Claims::getExpiration);
        return expiration.before(new Date());

    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System)).
                setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*100));
    }


    public Boolean validateToken(String token,UserDetails userDetails){
        final String username = getUsernameFormToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }


    //generate token from user
//Hi I am vijay-comment for pull request example
    //Hi I am vijay-comment for pull  example


}
