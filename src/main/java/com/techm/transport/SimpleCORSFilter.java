package com.techm.transport;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {//ContainerResponseFilter {  

@Override
public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub
	
}

@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
	((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
    ((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "*");
	chain.doFilter(request, response);
}

@Override
public void destroy() {
	// TODO Auto-generated method stub
	
}
}