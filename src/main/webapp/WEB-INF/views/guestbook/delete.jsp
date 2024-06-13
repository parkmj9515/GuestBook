<%@page import="guestbook.vo.GuestVo"%>
<%@page import="guestbook.dao.GuestbookOracle"%>
<%@page import="guestbook.dao.GuestbookDao"%>
    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 데이터베이스 접속 정보 확인1
    ServletContext context = getServletContext();

    String dbuser = context.getInitParameter("dbuser");
    String dbpass = context.getInitParameter("dbpass");
    String pass = request.getParameter("password");
    
    GuestbookDao dao = new GuestbookOracle(dbuser, dbpass);
    Long no = Long.parseLong(request.getParameter("no"));
	GuestVo vo = dao.get(no);
	
   	if (vo.getPassword().equals(pass)){
   		dao.delete(no);
   		response.sendRedirect(request.getContextPath() + "/guestbook/list.jsp");
	} else {
		
		%>
		<h1>비밀번호가 일치하지 않습니다.</h1>
		<a href="<%= request.getContextPath() + "/" %>">메인으로 돌아가기</a>
		<%
	}
%>