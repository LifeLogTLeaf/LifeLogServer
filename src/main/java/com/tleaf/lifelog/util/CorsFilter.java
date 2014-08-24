package com.tleaf.lifelog.util;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jangyoungjin on 7/24/14.
 */
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("filtering...");
        if (httpServletRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpServletRequest.getMethod())) {
            System.out.println("CORS...");
            httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with, sid, mycustom, smuser");
            httpServletResponse.addHeader("Access-Control-Max-Age", "1800");//30 min
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
