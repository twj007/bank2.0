<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String name = "";
	String password = "";
	double num = Math.random();//用于刷新浏览器图片缓存
	String vc_code = (String)request.getSession().getAttribute("VC_CODE");
	//对有cookie的用户设置用户名密码
	if(request.getCookies() != null){
		Cookie[] c = request.getCookies();
		for(Cookie ck:c){
			if("name".equals(ck.getName()))
				name = URLDecoder.decode(ck.getValue(), "UTF-8");
			if("password".equals(ck.getName()))
				password = ck.getValue();	
		}
	}		
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ZH_CN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>--请登陆--</title>
	<meta charset="utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="bank china">
	<meta http-equiv="description" content="bank login">
	<link rel="stylesheet" type="text/css" href="css/cssreset.css"/>
	<link rel="stylesheet" type="text/css" href="css/main.css"/>
 	<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
 	<script type="text/javascript">
 		$(document).ready(function(){
 			
 			var verify_data = false;
 			
 			
	 		$("#code").blur(function(){
	 			if($("#code").val() != ""){
		 			$.get("confirm",{"VC_code":$("#code").val()}, function(data){
						if(data == "true"){
						   $("#confirm").empty();
						   $("#confirm").append("验证码正确");
						   varify_data = data;					 	
						}else{
						   $("#confirm").empty();
						   $("#confirm").append("验证码不正确");
						}				
		 					
		 			});
		 		}else{
		 			$("#confirm").empty();
		 			$("#confirm").append("验证码不能为空");
		 		}	
	 	    });
	 			
	 		
 			$("#code").focus(function(){
 				$("#confirm").empty();
 			});
 			$(":text").focus(function(){
 				$("#if_name").empty();
 			});
 			$(":password").focus(function(){
 				$("#if_password").empty();
 			});
 			$(":text").focus(function(){
 				$("#if_verify").empty();
 			});
 			$("#loginin").submit(function(){
 				if($(":text").val() == ""){
 					$("#if_name").empty();
 					$("#if_name").append("用户名不能为空");
 					return false;
 				}
 				if($(":password").val() == ""){
 					$("#if_password").empty();
 					$("#if_password").append("密码不能为空");
 					return false;
 				}
 				if($("#code").val() != "" && verify_data && <%=vc_code%> == $("#code").val()){
 					$("#if_verify").empty();
 					$("#if_verify").append("验证码不能为空");
 					return false;
 				}
 				return true;
 			});
 			
 		});
 		function changePic(){
 				var time = new Date().getTime(); 
 				alert("");
				document.getElementById("verifycode").src="<%=request.getContextPath()%>/verify.jpg?time="+time;
		}
 	</script>
  </head>
		<style>
			body{
				width:1980px;
				height:1080px;
				background: url(img/bg.jpg) repeat;
				font: "微软雅黑" 24px;
				color: whitesmoke;
			}	
			#change:hover{
				color:red;
			}	
		</style>
  <body>
    
    <form id = "loginin" action = "login.do" method = "post" >
    	<div class="main">
    		<div id="hand">
    			<h1>MYBANK</h1>
    		</div>
    		
    		<div id="main_container">
    		<hr>
	    		<div id="body">
	    			<span  id="info">用户名:</span>
	    			<input type = "text" id="name" name = "username" value="${cookie.name.value }">
	    			
		    	</div>
		    	<span style="color: red;" id="if_name"></span>
		    	<div id="body">
		    		<span id="info_pass" >密码:</span>
		    		<input type = "password" id="password" name = "password" value="${cookie.password.value}">
		    		
		    	</div>
		    	<span style="color: red;" id="if_password"></span>
		    	<div id="body">
		    		<span id="info_verify">验证码:</span>
		    		<input type = "text" id = "code" style="width:50px;">
		    		<img id = "verifycode" onclick="changePic()" src="<%=request.getContextPath()%>/verify.jpg?num="+<%=num%>>
		    		<span id = "confirm" style="color: red;"></span>
		    		
		    	</div>
		    		<a id = "change" href="javascript:changePic();" style="color:black; cursor: pointer;">看不清楚？</a>
		    	<div id="sub_mit">
		    	    <input type = "submit" value = "提交" id = "sub">
		    	    <input style="position:absolute; right: 95px;" type="checkbox" name = "flag" id = "flag"><span  style="position:absolute; right: 26px; color: black;">记住密码</span>
	    		</div>
	    		<div>
	    			<a href = "findlost.jsp">忘记密码?</a>
	    			<a href = "register.html" style="position:absolute; right: 26px;">注册</a>
	    		</div>
    		</div>
    	</div>
    	    	
    </form>
  </body>
</html>
