	
	package com.servlet;

	import java.io.IOException;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	/**
	 * 比较传过来的验证码和生成的验证码是否一致
	 * */
	@SuppressWarnings("serial")
	public class Confirm extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			doPost(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String code = (String)req.getSession().getAttribute("VC_CODE");
			String confirm = req.getParameter("VC_code");
			System.out.println(code+confirm);
			if(code.equals(confirm)){
				resp.getWriter().print("true");
			}else{
				resp.getWriter().print("false");
			}
		}
	
		
	}
