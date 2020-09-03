package com.revature.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("CORS Filter levaraged");
		
		//If the response is not an HTTPServletResponse, then the filter
		// passes on the request and response to the next part of the chain.
		if(!(response instanceof HttpServletResponse)) {
			chain.doFilter(request, response);
			return;
		}
		
		//Must cast the response as a HTTPServletResponse
		//This is important because we're going to set 
		//headers, which are specific to HTTP
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//Setting the headers
		resp.setHeader("Access-Control-Allow-Origin", "null");
		//This allows all origins
		
		resp.setHeader("Access-Control-Allow-Headers", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		//This allows specific HTTP Verbs
		
		resp.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type"
				+"Access-Control-Request-Method, Access-Control-Request-Headers");
		//This allows specific HTTP Headers (there's a fair few)
		
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		//Credentials are allowed
		
		chain.doFilter(request, response);
		//Continues the filter chain
		
	}

}
