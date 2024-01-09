package com.blog.BlogAppApis.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //getjwt token, user  from response

        String requestToken=request.getHeader("Authorization");
        //token=Bearer uediu4r78y4rj
        System.out.println(requestToken);
        String username=null;
        String token=null;

        if (requestToken!=null  && requestToken.startsWith("Bearer")){
             token= requestToken.substring(7);
             try {
                 username=this.jwtTokenHelper.getUsernameFormToken(token);
             }
             catch (IllegalArgumentException e){
                 System.out.println("Unable to get token");
             }
             catch (ExpiredJwtException e){
                 System.out.println("Jwt token has expired");
             }
             catch (MalformedJwtException e){
                 System.out.println("invalid jwt");
             }

        }
        else {
            System.out.println("jwt token doesnt  begin with bearer");
        }
        //once we get the token ,we will validate
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validateToken(token,userDetails)){
                //everthing is fine,we need to authenticate
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                System.out.println("Invalid jwt token");
            }
        }
        else {
            System.out.println("username is null or context is not null");
        }

        filterChain.doFilter(request,response);
    }
}
