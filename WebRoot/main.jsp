<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ZH_CN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>left</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src = "js/jquery-2.1.0.js"></script>
	<script type="text/javascript" src = "js/validate.js"></script>
	<script>
		$(document).ready(function(){
			$("#inquiry_href").click(function(){
				$.get("inquiry.do", function(data){
					$("#main_page").empty();
					$("#main_page").append("<h1>当前余额为:"+data+"</h1>");
				});
				$("#main_page").css("display","block");
				$("#main_page1").css("display", "none");
				$("#main_page2").css("display", "none");
				$("#main_page3").css("display", "none");
			});
			
			$("#deposit_href").click(function(){
				$("#main_page1").css("display","block");
				$("#main_page").css("display", "none");
				$("#main_page2").css("display", "none");
				$("#main_page3").css("display", "none");
			});
			$("#deposit").submit(function(){
				if($("#balance").val() == ""){
					alert("金额不能为空");
					return false;
				}
				return isInteger($("#balance").val());
				
			});
			$("#withdrawals_href").click(function(){
				$("#main_page2").css("display","block");
				$("#main_page").css("display", "none");
				$("#main_page1").css("display", "none");
				$("#main_page3").css("display", "none");
			});
			$("#withdraw").submit(function(){
				
				if($("#money").val() == ""){
					alert("金额不能为空");
					return false;
				}
				return isInteger($("#money").val());
			});
			
			$("#transfer_href").click(function(){
				$("#main_page3").css("display","block");
				$("#main_page").css("display", "none");
				$("#main_page1").css("display", "none");
				$("#main_page2").css("display", "none");
			});
			$("#transfer").submit(function(){
				if($("#account").val() == ""){
					alert("账号不能为空");
					return false;
				}
				if($("#money2").val() == ""){
					alert("金额不能为空");
					return false;
				}
				return isInteger($("#money2").val());
				
			});
		});
	</script>
  </head>
  
   	<style type="text/css">
   		
   		#top{
   			width: 100%;
   			height: 20%;
   			background: url(img/bg.jpg) no-repeat;
   		}
   		#top h1{
   			text-align: right;
   			color: whitesmoke;
   			line-height: 500%;
   		}
   		#left{
   			background: #E3E3E3;
   			display: inline-block;
   			width: 25%;
   			height: 80%;
   		}
   		#right{
   			background: #E3E3E3;
   			display: inline-block;
   			width: 75%;
   			height: 80%;
   			position: absolute;
   			right: 10px;
   		}
   		#left span{
   			display: block;
   			width: 100%;
   			height: 5%;
   			background: url(img/bar.jpg) repeat-x;
   		}
   		#left ul{
   			list-style: none;
   			width: 100%;
   			height: 100%;
   		}
   		#left ul li{
   			display: block;
			margin-right: 20%;   			
   			width: 100%;
   			height: 10%;
   			background: #269ABC;
   			color: white;
   			text-align: center;
   			line-height: 250%;
   			position: relative;
			left: -40px;
   		}
   		#left li:hover{
   			background: #2B542C;
   			    cursor: pointer;
   		}
   		#left li a{
   			color: white;
   			text-decoration: none;
   		}
   		#left li a:hover{
   			text-decoration: underline;
   		}
   		#main_page, #main_page1, #main_page2, #main_page3{
   			position: absolute;
   			top: 30%;
   			right: 50%;
   		}	
   		#inquery, #withdraw, #transfer{
			position: relative;
			left: 100%;
   		}	
   		
   	</style>
  <body>
   
   <div id="main">
   	<div id="top"><h1>欢迎${name}来到银行系统2.0</h1></div>
   	<div id="left">
   		<span></span>
   		<ul>
   			<li><a  id="inquiry_href">查询余额</a></li>
   			<span></span>
   			<li><a id="deposit_href">存款</a></li>
   			<span></span>
   			<li><a id="withdrawals_href">取款</a></li>
   			<span></span>
   			<li><a id="transfer_href">转账</a></li>
   			<span></span>
   			<li><a href="logout.jsp">退出</a></li>
   		</ul>
   		
   	</div>
   	<div id="right">
   		<div id="main_page" style="display: none;">
   		</div>
   		<div id="main_page1" style="display: none;">
   			<form action="deposit.do" method="post" id="deposit">
   				存款金额:<input type="text" name="balance" id="balance"/>
   				<input type="submit" id="submit" value="存款"/>
   			</form>
   		</div>
   		<div id="main_page2" style="display: none;">
   			<form action="withdrawal.do" method="post" id="withdraw">
   				金额:<input type="text" name="money" id="money"/>
   				<input type="submit" id="submit" value="取款" />
   			</form>
   		</div>
   		<div id="main_page3" style="display: none;">
   			<form action="transfer.do" method="post" id="transfer">
   				收款人账号:<input type="text" name="account" id="account"/><br>
   				金额:<input type="text" name="money" id="money2"/>
   				<input type="submit" name="submit" value="转账"/>
   			</form>
   		</div>
   	</div>
   </div>
   
  </body>
</html>
