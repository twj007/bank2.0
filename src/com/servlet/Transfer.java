	package com.servlet;
	
	import java.io.IOException;
	
	import javax.servlet.ServletException;
	import javax.servlet.http.Cookie;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import com.manager.Manager;
	
	/***
	 * 转账
	 * 
	 * */
	public class Transfer extends HttpServlet {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = -3549143922814676663L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			this.doPost(request, response);
		}
	
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String account = request.getParameter("account");
			String money = request.getParameter("money");
			int id = 0;
			Manager manager = Manager.getInstance();
			Cookie[] c = request.getCookies();
			boolean flag = false;
			for(Cookie ck: c){
				if("id".equals(ck.getName()))
					id = Integer.parseInt(ck.getValue());
			}
			
			if(id !=0){
				flag = manager.transfer(account, id, Double.parseDouble(money));
				if(flag)
					request.getRequestDispatcher("success.jsp").forward(request, response);
				else
					response.sendRedirect("fail.jsp");
			}
		}
	
	}
