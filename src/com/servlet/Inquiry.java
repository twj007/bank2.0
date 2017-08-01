	package com.servlet;
	
	import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.Manager;
	
	public class Inquiry extends HttpServlet {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	
			this.doPost(request, response);
		}
	
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	
			Cookie[] c = request.getCookies();
			String sid = null;
			int id     = 0;
			Double balance  = null;
			Manager manager = Manager.getInstance();
			for(int i=0; i<c.length; i++){
				if("id".equals(c[i].getName())){
					sid = c[i].getValue();
				}
				
			}
			if(sid != null)
				id = Integer.parseInt(sid);
			if(id != 0)
				balance = manager.inquiry(id);
			if(balance != null){
				HttpSession session = request.getSession();
				session.setAttribute("balance", balance);
				System.out.println(balance);
				request.getRequestDispatcher("WEB-INF/jsp/balance.jsp").forward(request, response);
			}else{
				
				response.sendRedirect("main.jsp");
			}
		}
	
	}
