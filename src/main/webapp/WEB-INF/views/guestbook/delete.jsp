<%@page import="guestbook.vo.GuestVo"%>
<%@page import="guestbook.dao.GuestbookOracle"%>
<%@page import="guestbook.dao.GuestbookDao"%>
    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    try {
        // 요청에서 비밀번호 파라미터를 가져옵니다.
        String password = request.getParameter("password");

        // 서블릿 컨텍스트에서 데이터베이스 사용자명과 비밀번호를 가져옵니다.
        ServletContext servletContext = getServletContext();
        String dbuser = servletContext.getInitParameter("dbuser");
        String dbpass = servletContext.getInitParameter("dbpass");

        // 데이터베이스 접근 객체를 생성합니다.
        GuestbookDao dao = new GuestbookOracle(dbuser, dbpass);
        
        // 요청에서 'no' 파라미터를 Long 타입으로 변환합니다.
        Long no = Long.parseLong(request.getParameter("no"));

        // GuestVo 객체를 가져옵니다.
        GuestVo vo = dao.get(no);

        // 입력된 비밀번호와 데이터베이스에 저장된 비밀번호를 비교합니다.
        if (password.equals(vo.getPassword())) {
            // 비밀번호가 일치하면, 해당 방명록 항목을 삭제합니다.
            dao.delete(no);
            // 메인 페이지로 리다이렉트합니다.
            response.sendRedirect(request.getContextPath());
        } else {
            // 비밀번호가 일치하지 않으면, 오류 메시지를 출력합니다.
            %>
            <h1>비밀번호가 일치하지 않습니다.</h1> ';
            <a href="<%= request.getContextPath() %>">메인으로 돌아가기</a>
            <%
        }
    } catch (Exception e) {
        e.printStackTrace();
        %>
        <h1>오류가 발생했습니다.</h1>
        <a href="<%= request.getContextPath() %>">메인으로 돌아가기</a>
        <%
    }
%>