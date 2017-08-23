	
	package com.servlet;
	
	import java.io.IOException;
	import java.net.URLEncoder;
	import javax.servlet.ServletException;
	import javax.servlet.http.Cookie;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import com.bean.Register;
	import com.manager.Manager;
	
	/**
	 * 获取账号信息进行注册
	 * */
	public class EnRoll extends HttpServlet {
	
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
	
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			Manager manager = Manager.getInstance();
			Integer id = null;
			Register register = new Register(name, password, email);
			id = manager.register(register);
			System.out.println(id);
			
			if(id != null){
				Cookie c = new Cookie("id", id.toString());
				Cookie c1 = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
				response.addCookie(c);
				response.addCookie(c1);
				request.getRequestDispatcher("main.jsp").forward(request, response);
			}else{
				response.sendRedirect("fail.jsp");
			}
		}
	
	}
