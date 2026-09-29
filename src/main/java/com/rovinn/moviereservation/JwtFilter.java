package com.rovinn.moviereservation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.List;

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
           if(way.equals("/api/auth")) {
              chain.doFilter(req,res);
              return;
           }
           String authHeader = req.getHeader("Authorization");
           if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try{
                    String email = jwtUtil.extractEmail(token);
                    String role  = jwtUtil.extractRole(token);
                    if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                        UsernamePasswordAuthenticationToken username = new UsernamePasswordAuthenticationToken(email, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(username);
                    }

                }
                   catch (Exception e) {

                       SecurityContextHolder.clearContext();
                }
           }
           chain.doFilter(req,res);
       }
}
