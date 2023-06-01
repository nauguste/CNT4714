// Servlet that uses session-tracking to keep per-client
//  access counts. Also shows other info about the session.

import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*; 

public class ShowSession extends HttpServlet {
  @Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    HttpSession session = request.getSession();
    String heading;
    Integer accessCount = (Integer)session.getAttribute("accessCount");
    if (accessCount == null) {
      accessCount = new Integer(0);
      heading = "Welcome, Newcomer";
    } else {
      heading = "Welcome Back";
      accessCount = new Integer(accessCount.intValue() + 1);
    }
    // Integer is an immutable data structure. So, you
    // cannot modify the old one in-place. Instead, you
    // have to allocate a new one and redo setAttribute.
    session.setAttribute("accessCount", accessCount);
    PrintWriter out = response.getWriter();
    String title = "Session Tracking Example";
    String docType = "<!DOCTYPE html\">";
    // build HTML document
    out.println(docType + "<head><title>" + title + "</title><meta charset \"utf-8\"> "
    		+ "<style type=\"text/css\"> <!--  body{ background-image:url(images/background.jpg); "
    		+ "font- Verdana, Arial, sans-serif; font-size:x-large;} h1,h2{text-align:center;}"
    		+ "table{border:2px solid black; margin:auto; font-size:x-large;}"
    		+ "td{padding:7px;} th{background-color:red; color:black;}"
    		+ "tr{background-color:blue; color:white;} .different{background-color:black; color:lime;}-->"
    		+ " </style> </head><body>\n");
 	out.println ("<br>");
    out.println(docType + "<h1>" + heading + "</h1>\n" + "<h2>Information on Your Session:</h2>\n"
    			+ "<table>"
                + "<tr><th class=\"different\">Info Type</th><th class=\"different\">Value<th></tr>" 
                + "<tr><th>ID</th><td>" + session.getId() + "</td></tr>" 
                + "<tr><th>Creation Time</th><td>" + new Date(session.getCreationTime()) + "</td></tr>"
                + "<tr><th>Time of Last Access</th><td>" + new Date(session.getLastAccessedTime()) + "</td></tr>"
                + "<tr><th>Number of Previous Accesses </th><td>" + accessCount + "</td></tr>"
                +"</table></body></html>");
               
  }
}

