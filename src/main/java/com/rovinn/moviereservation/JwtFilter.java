package com.rovinn.moviereservation;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    protected void doFilterInternal(HttpServletRequest req,@NonNull HttpServletResponse res,@NonNull FilterChain chain) throws
            ServletException, IOException
       {
           String way = req.getServletPath();
           if(way.equals("/users/login") || way.equals("/users/register")) {
              chain.doFilter(req,res);
              return;
           }
           String authHeader = req.getHeader("Authorization");
           if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try{
                    String email = jwtUtil.extractEmail(token);
                    UsernamePasswordAuthenticationToken username =  new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(username);

                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
           }
           chain.doFilter(req,res);
       }
}
