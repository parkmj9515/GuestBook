<%@ page import="guestbook.dao.GuestbookDao, guestbook.dao.GuestbookOracle" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    Long id = Long.parseLong(request.getParameter("id"));

    ServletContext context = getServletContext();
    String dbuser = context.getInitParameter("dbuser");
    String dbpass = context.getInitParameter("dbpass");

    GuestbookDao dao = new GuestbookOracle(dbuser, dbpass);
    boolean success = dao.delete(id);

    if (success) { // DELETE 성공
        response.sendRedirect("list.jsp");
    } else {
        %>
        <h1>Error</h1>
        <p>데이터 삭제 중 오류가 발생했습니다.</p>
        <%
    }
%>