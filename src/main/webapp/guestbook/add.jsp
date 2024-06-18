<%@page import="guestbook.vo.GuestVo"%>
<%@page import="guestbook.dao.GuestbookOracle"%>
<%@page import="guestbook.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

ServletContext servletContext = getServletContext();
String dbuser = servletContext.getInitParameter("dbuser");
String dbpass = servletContext.getInitParameter("dbpass");


	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String content = request.getParameter("content");
	
	
	
	GuestVo vo = new GuestVo(name, password, content);
	GuestbookDao dao = new GuestbookOracle(dbuser, dbpass);
	

	boolean success = dao.insert(vo);
	
	if (success)
		response.sendRedirect(request.getContextPath());
	else {
		%>
		<h1>Error</h1>
		<p>데이터 입력 중 오류가 발생했습니다</p>
		<%
	}
	
%>