// A simple servlet use to handle a response back to a JSP frontend.

//import javax.servlet.*;
//import javax.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class WelcomeServletSession extends HttpServlet {   
    // process "get" requests from clients
    protected void doGet( HttpServletRequest request, 
                         HttpServletResponse response ) throws ServletException, IOException 
    {
      String firstName = request.getParameter("firstName");
      String message = "Hello <span>" + firstName + "</span>! <br> Welcome to the world of ";
      message += "<span id='servlet'>servlet </span> and <span id='jsp'>jsp </span>technology.";
      HttpSession session = request.getSession();
      session.setAttribute("message",  message);;
      session.setAttribute("firstName",firstName);
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome - SessionVersion.jsp");
      dispatcher.forward(request, response);      
    }   //end doGet() method
    
    public void doPost(HttpServletRequest request, HttpServletResponse response )
    	throws IOException, ServletException {
    		doGet(request, response);
        	}

} //end WelcomeServletSession class
