package com.htp.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InputController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public InputController() {
    super();
    System.out.println("InputController constructor");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("InputController: doGet");
    String info = req.getParameter("input1");
    processRequest(req, resp, "doGet; info: " + info);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("InputController: doPost");
    String info = req.getParameter("input1");
    processRequest(req, resp, "doPost; info: " + info);
  }

  private void processRequest(HttpServletRequest req, HttpServletResponse resp, String sText)
      throws IOException {
    System.out.println("InputController: processRequest(req, resp)");
    resp.setContentType("text/html");

    PrintWriter out = resp.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv=\"Content-Type\"content=\"text/html; charset=utf-8\">");
    out.println("<title>InputController</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>");
    out.println("InputController info:" + sText);
    out.println("</h1>");
    out.println("<br>");
    out.println("</body>");
    out.println("</html>");
  }
}
