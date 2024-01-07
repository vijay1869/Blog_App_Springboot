package com.blog.BlogAppApis.security;
//has methods to perform token related operations


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenHelper {
    public static final long JWT_TOKEN_VALIDITY=5*60*60;//milli seconds?
    private String secret="JwtTokenKey";
    //retrive username from jwt token
//    public String getUsernameFromToken(String token){
//
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//    //get expiration date
//    public Date getExpirationDateFromToken(String token){
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//    //
//    public <T> T getClaimFromToken(Token token, Function<Claims,T> claimResolver){
//        final Claims claims=getAllClaimsFromToken(token);
//        return claimResolver.apply(claims);
//    }
//
//    //for retriveing any token from token we need the secrete key
//    public Claims getAllClaimsFromToken(Token token){
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
////check whether it is expeied
//    public boolean isTokenExpired(Token token){
//        final Date expiration=getClaimFromToken(token,Claims::getExpiration);
//        return expiration.before(new Date());
//
//    }

    //generate token from user
//Hi I am vijay-comment for pull request example


}
