import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

//  Creates a table of the cookies associated with the current page. Also sets six cookies: 
//  three that apply only to the current session (regardless of how long that session lasts)
//  and three that persist for an hour (regardless of whether the browser is restarted).
//  
//

public class CookieTest extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
    for(int i=0; i<3; i++) {
      // Default maxAge is -1, indicating cookie
      // applies only to current browsing session.
      Cookie cookie = new Cookie("Session-Cookie-" + i, "Cookie-Value-S" + i);
      response.addCookie(cookie);
      cookie = new Cookie("Persistent-Cookie-" + i, "Cookie-Value-P" + i);
      // Cookie is valid for an hour, regardless of whether
      // user quits browser, reboots computer, or whatever.
      cookie.setMaxAge(900); //cookie expires in 15 minutes
      response.addCookie(cookie);
    } 
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType = "<!DOCTYPE HTML>\n";
    String title = "Active Cookies";
	out.println ("<body bgcolor=white background=images/background.jpg lang=EN-US link=blue vlink=blue >");
    out.println ("<body style='tab-interval:.5in'>");
    out.println ("<body style=font-family: font-family:Verdana, Arial, sans-serif; >");
	out.println ("<font size = 5>");
	out.println ("<br>");
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                "<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "  <TH>Cookie Name\n" +
                "  <TH>Cookie Value");
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      out.println("<TR><TH COLSPAN=2>No cookies");
    } else {
      Cookie cookie;
      for(int i=0; i<cookies.length; i++) {
        cookie = cookies[i];
        out.println("<TR>\n" +
                    "  <TD>" + cookie.getName() + "\n" +
                    "  <TD>" + cookie.getValue());
      }
    }
    out.println("</TABLE></BODY></HTML>");
  }
}

