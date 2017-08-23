	package com.servlet;
	
	import java.io.IOException;
	import javax.servlet.ServletException;
	import javax.servlet.http.Cookie;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

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
	
		/**
		 * 查询余额，根据从cookie中获取的id查询对应的字段的money字段，返回
		 * 
		 * */
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
				//通过流返回余额，前端ajax获取数据显示
				response.getWriter().write(balance.toString());
				//System.out.println(balance);
				
			}else{
				
				response.sendRedirect("main.jsp");
			}
		}
	
	}
