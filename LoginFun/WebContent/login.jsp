<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome！</title>
<style type="text/css">

body {
    background: url(image/road.jpg);
    background-size:100%;
   
	border:1px solid #000000
	float:right;
	text-align:center;
	font-family:"站酷酷黑";
}
</style>
</head>
<body>
	<h3>登录！</h3>
	<span style = "color:red;font-weight:bold">
	<%
	if(request.getAttribute("error")!=null)
		out.println(request.getAttribute("error")+"<br>");
	%>
	</span>
<form action = "LoginServlet" method = "post">
	用户名:<p><input type = "text" name = "name"></p>
	密码:<p><input type = "password" name = "pass"></p>
		<input type = "submit" value = "登录"> 
		<p> <a href="regist.jsp">注册账户！</a></p>
</form>
</body>
</html>