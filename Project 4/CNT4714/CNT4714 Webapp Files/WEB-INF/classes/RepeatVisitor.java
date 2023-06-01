
import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;//

// Servlet that says "Welcome aboard" to first-time visitors and "Welcome back" to repeat visitors.

public class RepeatVisitor extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    boolean newbie = true;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for(int i=0; i<cookies.length; i++) {
        Cookie c = cookies[i];
        if ((c.getName().equals("repeatVisitor")) &&
            // Could omit test and treat cookie name as a flag
            (c.getValue().equals("yes"))) {
          newbie = false;
          break;
        }
      }
    }
    String title;
    if (newbie) {
      Cookie returnVisitorCookie =
        new Cookie("repeatVisitor", "yes");
     // returnVisitorCookie.setMaxAge(60*60*24*365); // 1 year
	  returnVisitorCookie.setMaxAge(60); //cookie expires in 1 minute
      response.addCookie(returnVisitorCookie);
      title = "Welcome Aboard";
    } else {
      title = "Welcome Back";
    }
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType = "<!DOCTYPE html\">";
    // start HTML document
    // head section of document
    out.println( "<head>" );
    out.println( "<title>Repeat Visitor Servlet</title>" );
    out.println( "<meta charset=\"utf-8\">" );
    out.println( "<style type='text/css'>");
    out.println( "<!--  body{background-color:navy; color:yellow; font-family:Verdana, Arial, sans-serif; font-size:large; h1:text-align:center;} -->");
    out.println( "</style>");
    out.println( "</head>" );
    // body section of document
    out.println( "<body>" );
	out.println ("<br>");
    out.println(docType +
                "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY>\n" + "<H1>" + title + "</H1>\n" + "</BODY></HTML>");
  }
}

