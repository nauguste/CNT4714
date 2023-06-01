//import javax.servlet.*;
//import javax.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

public class CurrentTimeServlet extends HttpServlet {
  /** Process the HTTP Get request */
  public void doGet(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
	 out.println ("<body bgcolor=white background=images/background.jpg lang=EN-US link=blue vlink=blue >");
    out.println ("<body style='tab-interval:.5in'>");
	 out.println ("<font size = 5>");
	 out.println ("<br>");
    out.println("<p>The current time is " + new Date() + "</p>" );
    out.println("</body>");
    out.close(); // Close stream
  }
} 