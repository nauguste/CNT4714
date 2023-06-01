// Servlet that prints per-client access counts.
// Note: this class requires the CookieUtilities class

import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

 
public class ClientAccessCounts extends HttpServlet {
  @Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String countString = CookieUtilities.getCookieValue(request, "accessCount", "1");
    int count = 1;
    try {
      count = Integer.parseInt(countString);
    } catch(NumberFormatException nfe) { }
    Cookie c = new Cookie("accessCount", String.valueOf(count+1));
    c.setMaxAge(900);
    response.addCookie(c);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Access Count Servlet";
    String docType = "<!DOCTYPE html\">";
    // build HTML document
    out.println(docType + "<head><title>" + title + "</title><meta charset \"utf-8\"> "
    		+ "<style type=\"text/css\"> <!--  body{ background-image:url(images/background.jpg);"
    		+ "font-family:Verdana, Arial, sans-serif; font-size:x-large;} h1 {color:red; font-weight: 900; font-size: 42pt;}"
    		+ "h1, h2{text-align:center;} span {color:blue;}--> </style> </head><body>\n");
 	out.println ("<br>");
    out.println(docType + "<h1>" + title + "</h1>\n" +
                "<h2>This is visit number <span>" + count + " </span>by this browser.</h2>\n" + "</body></html>");
  }
}

