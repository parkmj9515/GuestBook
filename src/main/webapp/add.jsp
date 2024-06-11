<%@page import="guestbook.vo.GuestVo"%>
<%@page import="guestbook.dao.GuestbookOracle"%>
<%@page import="guestbook.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 데이터베이스 접속 정보 확인
    ServletContext context = getServletContext();

    String dbuser = context.getInitParameter("dbuser");
    String dbpass = context.getInitParameter("dbpass");

    // 폼 입력 데이터
    String name = request.getParameter("name");
    String password = request.getParameter("pass");
    String content = request.getParameter("content");

    boolean success = false;
    String errorMessage = null;

    try {
        if (name != null && !name.isEmpty() && password != null && !password.isEmpty() && content != null && !content.isEmpty()) {
            long parsedPassword = Long.parseLong(password); // 변환 시도
            GuestVo vo = new GuestVo(null, name, parsedPassword, content, new java.util.Date());
            GuestbookDao dao = new GuestbookOracle(dbuser, dbpass);
            success = dao.insert(vo);
        } else {
            errorMessage = "입력값이 유효하지 않습니다.";
        }
    } catch (NumberFormatException e) {
        errorMessage = "비밀번호는 숫자여야 합니다.";
    } catch (Exception e) {
        errorMessage = "데이터 입력 중 오류가 발생했습니다.";
        e.printStackTrace(); // 문제 해결을 위해 발생한 예외를 로그에 기록
    }

    if (success) { // INSERT 성공
        response.sendRedirect("list.jsp");
    } else {
        %>
        <h1>Error</h1>
        <p><%= errorMessage %></p>
        <%
    }
%>