package com.htp.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static int nGetCounter;
  private static int nPutCounter;

  public Controller() {
    super();
    System.out.println("Controller constructor");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // super.doGet(req, resp);
    nGetCounter++;
    System.out.println("Controller: doGet");
    processRequest(req, resp, "doGet; counter: " + nGetCounter);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //  super.doPost(req, resp);
    nPutCounter++;
    System.out.println("Controller: doPost");
    processRequest(req, resp, "doPost; counter: " + nPutCounter);
  }

  private void processRequest(HttpServletRequest req, HttpServletResponse resp, String sActiontext)
      throws IOException {
    System.out.println("Controller: processRequest(req, resp)");
    resp.setContentType("text/html");

    PrintWriter out = resp.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv=\"Content-Type\"content=\"text/html; charset=utf-8\">");
    out.println("<title>Controller</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>");
    out.println("Controller " + sActiontext);
    out.println("</h1>");
    out.println("<br>");
    out.println("</body>");
    out.println("</html>");
  }
}
