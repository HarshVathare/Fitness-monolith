package com.project.fitness.Security;

//this is the filter for validate any incoming request

import com.project.fitness.Entity.User;
import com.project.fitness.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

//    @Autowired
//    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("AuthTokenFilter Called ...");

        log.info("incoming request :- {} ", request.getRequestURI());

        final String requestTokenHeader = request.getHeader("Authorization");
        //Authorization has Bearer Token
        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //if Condition is true and get token proper format then
        String token = requestTokenHeader.substring(7);
        System.out.println("Jwt-Token :- "+token);

        Claims claims = jwtUtils.getAllClaims(token);
        Object roles = claims.get("roles");

        System.out.println("ROLES :- "+roles);

        String User_Id = jwtUtils.getUsernameFromToken(token);
        if(User_Id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findById(User_Id).orElseThrow(); //get All user data

            // Get ContextHolder and set Parameter
            UsernamePasswordAuthenticationToken updateContext =
                    new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

            // then Update Context Holder
            SecurityContextHolder.getContext().setAuthentication(updateContext);
        }
        filterChain.doFilter(request, response);


    }

}
