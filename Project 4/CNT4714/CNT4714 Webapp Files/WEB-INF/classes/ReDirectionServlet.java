// Redirecting a client to a different Web page.
//import javax.servlet.*;
//import javax.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class ReDirectionServlet extends HttpServlet {   

   // process "get" request from client
   protected void doGet( HttpServletRequest request, 
      HttpServletResponse response )
         throws ServletException, IOException 
   {
      String location = request.getParameter( "page" );

      if ( location != null ) 
         
         if ( location.equals( "CNT4714" ) )
            response.sendRedirect( "http://www.ucf.edu" );
         else 
            if ( location.equals( "alpha" ) )
               response.sendRedirect( "alpha" );
				else
				    if ( location.equals ("cyclingnews") )
					    response.sendRedirect( "http://www.cyclingnews.com" );
					 else
				       if ( location.equals ( "error" ) )
					       response.sendRedirect( "error"  );

      // code that executes only if this servlet does not redirect the user to another page

      response.setContentType( "text/html" );
      PrintWriter out = response.getWriter();  

      // start HTML document
      out.println( "<!DOCTYPE html\">" ); 
      // head section of document
      out.println( "<head>" );
      out.println( "<title>Invalid page</title>" );
      out.println( "<meta charset=\"utf-8\">" );
      out.println( "<style type='text/css'>");
      out.println( "<!--  body{background-color:red; font-family:calibri;} -->");
      out.println( "</style>");
      out.println( "</head>" );
      // body section of document
      out.println( "<body>" );
      out.println( "<h1>Invalid page requested</h1>" );
      out.println( "<p><a href = " + "RedirectionServlet.html\">" );
      out.println( "Click here for more details</a></p>" );
      out.println( "</body>" );
      // end HTML document
      out.println( "</html>" );
      out.close();  // close stream to complete the page  
   }   
}



