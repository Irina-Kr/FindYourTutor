package by.tms.findyourtutor.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTTokenFilter{
    private static final String AUTHORIZATION = "Authorization";
    private final JWTTokenProvider jwtTokenProvider;

    @Override
    private void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
       String token = jwtTokenProvider.resolveToken(request);
        if(token != null && jwtTokenProvider.validateToken(token)){
           Authentication auth = jwtTokenProvider.getAuthentication(token);
           if(auth != null){
               SecurityContextHolder.getContext().setAuthentication(auth);
           }
       }
       filterChain.doFilter((request, response);
    }

}
