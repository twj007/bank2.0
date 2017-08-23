	package com.servlet;
	
	import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.bean.Account;
import com.manager.Manager;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
	
	public class Login extends HttpServlet {
		
		/**
		 * 登陆验证
		 */
		private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			this.doPost(request, response);
		}
	
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String name     = request.getParameter("username");
			String password = request.getParameter("password");
			String flag     = request.getParameter("flag");
			Manager manager = Manager.getInstance();
			Account account = new Account(name, password);
			
			//获取验证成功的用户名
			int count = manager.confirmLogin(account);
			System.out.println(flag);	
			if(count != 0){
				if(flag == null){
					System.out.println("no cookie login");
					Cookie c = new Cookie("id", new Integer(count).toString());
					Cookie c1 = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
					//c.setHttpOnly(true);
					c.setMaxAge(60*5);
					//c1.setHttpOnly(true);
					c1.setMaxAge(60*5);
					response.addCookie(c);
					response.addCookie(c1);
					request.getSession().setAttribute("name", name);
					request.getRequestDispatcher("main.jsp").forward(request, response);
					
				}else{
					System.out.println("cookie login");
					System.out.println(password);
					Cookie c = new Cookie("id", new Integer(count).toString());
					Cookie c1 = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
					Cookie c2 = new Cookie("password", password);
					//c.setHttpOnly(true);//防止脚本读取cookie
					//c1.setHttpOnly(true);
					//c2.setHttpOnly(true);
					/*创建的 Cookie 会被以安全的形式向服务器传输，也就是只能在 HTTPS 连接中被浏览器传递到服务器端进行会话验证，
					如果是 HTTP 连接则不会传递该信息，所以不会被盗取到Cookie 的具体内容*/
					//c.setSecure(true);
					//c.setMaxAge(1000);
					response.addCookie(c);
					response.addCookie(c1);
					response.addCookie(c2);
					request.getSession().setAttribute("name", name);
					request.getRequestDispatcher("main.jsp").forward(request, response);
				}	
			}else{
				response.sendRedirect("fail.jsp");
			}	
				
			
		}
	
	}
