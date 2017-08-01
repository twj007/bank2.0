package com.servlet;

import java.io.IOException;

import javafx.application.Application;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.Manager;

public class Deposit extends HttpServlet {

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
		
		Double money = Double.parseDouble(request.getParameter("money"));
		Manager manager = Manager.getInstance();
		int id = 0;
		Cookie[] c = request.getCookies();
		for(Cookie ck:c){
			if("id".equals(ck.getName()))
				id = Integer.parseInt(ck.getValue());
		}
		if(id != 0){
			int result = manager.getDeposit(money, id);
			if(result != -1){
				Double balance = manager.inquiry(id);
				HttpSession session = request.getSession();
				session.setAttribute("balance", balance);
				request.getRequestDispatcher("WEB-INF/jsp/balance.jsp").forward(request, response);
			}else{
				response.sendRedirect("fail.jsp");
			}
			
		}else{
			response.sendRedirect("fail.jsp");
		}	
	}

}
