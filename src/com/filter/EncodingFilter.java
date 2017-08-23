	
	package com.filter;

	import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	
	/**
	 * 编码拦截
	 * 
	 * */
	public class EncodingFilter implements Filter {

		private String encoding = null;
		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			encoding = filterConfig.getInitParameter("encoding");
		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException {
			
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse resp = (HttpServletResponse)response;
			req.setCharacterEncoding(encoding);
			resp.setCharacterEncoding(encoding);
			chain.doFilter(req, resp);
		}

		@Override
		public void destroy() {
			
		}
	
	}
