package com.Ecommerce.web.application.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        log.info("Authorization header {}", authorization);

        String username = null;
        String token = null;

        if (authorization != null && authorization.startsWith("Bearer")) {
            token = authorization.substring(7);

            try {
                username = helper.getUsernameFromToken(token);
                log.info("Token username {}", username);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                log.info("illegal argument while fetching the argument " + e.getMessage());
            } catch (ExpiredJwtException ex) {
                log.info("Given JWT is Expired " + ex.getMessage());
            } catch (MalformedJwtException ex) {
                log.info("Some changed has done in token " + ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } else {
            log.info("Invalid Header");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //valid token
            if (username.equals(userDetails.getUsername()) && !helper.isTokenExpired(token)) {
                //security conte    ct ke andar authentication set karenge

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        ;
        filterChain.doFilter(request, response);
    }


}

