package guestbook.controller;

import java.io.IOException;
import java.util.List;

import guestbook.dao.GuestbookDao;
import guestbook.dao.GuestbookOracle;
import guestbook.vo.GuestVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "GuestBookServlet", urlPatterns = "/el")
public class GusetBookServlet extends BaseServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String actionName = req.getParameter("a");
		  if ("form".equals(actionName)) {
	            // 사용자 입력 페이지로 FORWARD
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
	            rd.forward(req, resp);
	        } else {
	            // 목록 받아오는 부분 -> /el
	            GuestbookDao dao = new GuestbookOracle(dbuser, dbpass);
	            List<GuestVo> list = dao.getList();

	            // list를 요청 객체에 추가
	            req.setAttribute("list", list);
	            // list 객체를 jsp로 FORWARD
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/iist.jsp");
	            rd.forward(req, resp);
	        }
	    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String actionName = req.getParameter("a");

	    if ("insert".equals(actionName)) {
	        // insert 기능 수행
	        String name = req.getParameter("name");
	        String password = req.getParameter("password");
	        String content = req.getParameter("content");

	        GuestVo vo = new GuestVo();
	        vo.setName(name);
	        vo.setPassword(password);
	        vo.setContent(content);

	        // Get necessary parameters from servlet context
	        ServletContext servletContext = getServletContext();
	        String dbuser = servletContext.getInitParameter("dbuser");
	        String dbpass = servletContext.getInitParameter("dbpass");

	        GuestbookDao dao = new GuestbookOracle(dbuser, dbpass);
	        boolean success = dao.insert(vo);

	        if (success) {
	            System.out.println("INSERT SUCCESS");
	        } else {
	            System.out.println("INSERT FAILED");
	        }

	        // Redirect to appropriate path
	        resp.sendRedirect(req.getContextPath() + "/el");
	    } else if ("delete".equals(actionName)) {
	        // delete 기능 수행
	        // Get necessary parameters from request
	        Long no = Long.parseLong(req.getParameter("no"));

	        // Get necessary parameters from servlet context
	        ServletContext servletContext = getServletContext();
	        String dbuser = servletContext.getInitParameter("dbuser");
	        String dbpass = servletContext.getInitParameter("dbpass");

	        // Instantiate GuestbookDao and perform delete operation
	        GuestbookDao dao = new GuestbookOracle(dbuser, dbpass);
	        boolean success = dao.delete(no);

	        // Check if delete operation was successful
	        if (success) {
	            System.out.println("DELETE SUCCESS");
	        } else {
	            System.out.println("DELETE FAILED");
	        }

	        // Redirect to appropriate path
	        resp.sendRedirect(req.getContextPath() + "/el");
	    } else {
	        super.doPost(req, resp);
	    }
}
}