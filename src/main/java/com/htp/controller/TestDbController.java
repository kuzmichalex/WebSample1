package com.htp.controller;

import com.htp.dao.UserDao;
import com.htp.dao.UserDaoImpl;
import com.htp.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class TestDbController extends HttpServlet {
	public TestDbController() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("TestDbController doPost");
		processRequest(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("TestDbController doGet");
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("InputController: processRequest(req, resp)");
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\"content=\"text/html; charset=utf-8\">");
		out.println("<title>TestDbController</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");
		out.println("req.getParameter all:" + req.getParameter("all"));
		out.println("</h1>");

		out.println("<table>");

		UserDao userDao = new UserDaoImpl();
		List<User> userList = userDao.findAll();
		for (User next : userList) {
			out.println("<tr>");
			out.println("<td style=\"background-color: cyan\">" + next.getId()+ "</td>");
			out.println("<td style=\"background-color: red\">" + next.getName()+ "</td>");
			out.println("<td style=\"background-color: blue\">" + next.getLogin()+ "</td>");
			out.println("<td style=\"background-color: yellow\">" + next.getBirthDate()+ "</td>");
			out.println("</tr>");
		}
		out.println("</table>");

		out.println("<br>");
		out.println("</body>");
		out.println("</html>");
	}
}
