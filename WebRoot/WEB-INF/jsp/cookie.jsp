	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@page import="java.net.URLDecoder"%>
	<%
	String id = null;
	String name = null;
	Cookie[] c = request.getCookies();
		if(c != null){
			for(int i = 0; i<c.length; i++){
				if("id".equals(c[i].getName())){
					id = c[i].getValue();
				}
				if("name".equals(c[i].getName())){
					name = URLDecoder.decode(c[i].getValue(), "UTF-8");
				}
			}
			if(id == null){
				response.sendRedirect("index.jsp");
			}
		}
	%>	