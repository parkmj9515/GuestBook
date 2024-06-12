<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestBook</title>
</head>
<body>
<h1>GuestBook</h1>
<ul>
	<li><a 
		href="<%= request.getContextPath() + "/guestbook/" %>">
			Model1 방식</a></li>
	<li><a 
		href="<%= request.getContextPath() + "/guestbook" %>">Model 2 방식 (Servlet + JSP)</a></li>
</ul>


</body>
</html>