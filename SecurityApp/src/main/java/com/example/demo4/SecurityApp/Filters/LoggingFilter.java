package com.example.demo4.SecurityApp.Filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Long startTime = System.currentTimeMillis();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("Incoming request: {} {}",method,uri);
        filterChain.doFilter(request,response);
        int status = response.getStatus();
        long timeTaken = System.currentTimeMillis()-startTime;
        log.info("Outgoing Response: {} {} | Status: {} | Time: {} ms",
                method, uri, status, timeTaken);
    }

}
