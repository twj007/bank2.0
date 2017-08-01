<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
 
  		<frameset rows="20%,*" border="0">
  			<frame src ="jsp/top.jsp" noresize="noresize"></frame>
  			<frameset cols="30%,*">
  				<frame src="jsp/left.jsp"  noresize="noresize"></frame>
  				<frame src="jsp/right.jsp" name="right" noresize="noresize"></frame>
  			</frameset>
  		</frameset>

		<noframes>
			<body>您的浏览器无法处理框架！</body>
		</noframes>
</html>
