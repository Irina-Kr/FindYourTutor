package by.tms.findyourtutor.configuration;

import jakarta.servlet.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public  class JWTTokenFilter implements Filter {
    private static final String AUTHORIZATION = "Authorization";
    private final JWTTokenProvider jwtTokenProvider;




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
    @Override
    public void destroy () {
    }

    }


