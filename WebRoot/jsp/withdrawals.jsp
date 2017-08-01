<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'withdrawals.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src = "js/jquery-2.1.0.js"></script>
	<script type="text/javascript" src = "js/isInt.js"></script>
	
	<script>
		$(document).ready(function(){
			
			$("#withdraws").submit(function(){
			
				if($("#my1").val() == ""){
					alert("金额不能为空");
					return false;
				}
				return isInteger($("#my1").val());
				
			});
		});
	</script>

  </head>
  
  <body>
   	<form action = "withdrawal" id = "withdraws" style="text-align:center;" method = "post">
   		金额:<input type = "text" name = "money" id = "my1"><br>
   	        <input type = "submit" value = "提交"></form>
  </body>
</html>
