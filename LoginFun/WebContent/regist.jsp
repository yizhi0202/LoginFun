<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>热烈欢迎陈学姐莅临注册界面！</title>
<link rel="stylesheet" type="text/css" href="mycss/index.css" />
</head>
<body>
<h3>注册！</h3>
	<span style="color:red;font-weight:bold">
	<%
	if(request.getAttribute("error")!=null)
		out.println(request.getAttribute("error")+"<br>");
	%>
	</span>
<form action = "RegistServlet" method = "post">
	用户名:<p><input type = "text" name = "name"></p>
	密码:<p><input type = "password" name = "pass"></p>
		<input type = "submit" value = "注册">
</form>
</body>
</html>