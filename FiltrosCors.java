package com.softcaribbean.gestionempleados.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FiltrosCors extends GenericFilterBean {

    private static final String OPTIONS = "OPTIONS";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin",
                "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials",
                "true");
        httpResponse
                .setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE, PUT");
        httpResponse.setHeader("Access-Control-Allow-Headers",
                "x-requested-with, Content-Type, Authorization");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (OPTIONS.equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

}