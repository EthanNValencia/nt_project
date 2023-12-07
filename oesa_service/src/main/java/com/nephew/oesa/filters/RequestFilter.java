package com.nephew.oesa.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements Filter {
	
	private final Logger logger = LoggerFactory.getLogger(RequestFilter.class);
	
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        // HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        logger.info("Remote Address: " + request.getRemoteAddr());
        logger.info("Remote Port: " + request.getRemotePort());
        logger.info("Remote Host: " + request.getRemoteHost());
        logger.info("Remote Headers: " + request.getHeaderNames());
        logger.info("Remote Request URI: " + request.getRequestURI());
        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
            try {
                chain.doFilter(req, res);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
        	logger.warn("Pre-flight failure.");
        }
    }

}