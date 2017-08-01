	
	package com.servlet;

	import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;
	
	
	@SuppressWarnings("serial")
	public class Kaptcha extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			this.doPost(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			VerifyCode vc = new VerifyCode();
			BufferedImage image = vc.getImage();
			req.getSession().setAttribute("VC_CODE", vc.getText());
			System.out.println(vc.getText());
			VerifyCode.output(image, resp.getOutputStream());
			resp.getOutputStream().flush();
		}
		
	
	}
