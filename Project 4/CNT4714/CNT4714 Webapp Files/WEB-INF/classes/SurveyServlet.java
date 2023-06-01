// SurveyServlet.java
// A Web-based survey that uses JDBC from a servlet.

import java.sql.*;
import java.lang.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.MysqlDataSource;
// the imports below commented out are for Tomcat version 9.x.x and earlier.  Tomcat 10.0.x and later all use jakarta
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.UnavailableException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class SurveyServlet extends HttpServlet 
{
   private Connection connection;
   private Statement statement;

   // set up database connection and create SQL statement
   public void init( ServletConfig config ) throws ServletException
   {
       // attempt database connection and create Statement
	   // I've illustrated four different techniques for establishing the connection to the database from the servlet running in a webapp on Tomcat
	   // They are listed in decreasing order of preference, although Techniques 1 and 2 are essentially equally viable.
	   // Technique 1: using a properties file located in the webapp/WEB-INF/lib folder.
	   // Technique 2: using initialization parameters in the web.xml file of the webapp.
	   // Technique 3: using a dataSource object, but no properties file - hardcoded dataSource properties.
	   // Technique 4: using the static loading of the driver and connection details - this is the least preferred method.
	  
	   // TECHNIQUE 1: using a properties file  
	   //--------------------------------------
	    Properties properties = new Properties();
	    FileInputStream filein = null;
	    MysqlDataSource dataSource = null;
	    //read a properties file
	    try {
	    	filein = new FileInputStream("/Library/Tomcat10016/webapps/CNT4714/WEB-INF/lib/db.properties");
	    	properties.load(filein);
	    	dataSource = new MysqlDataSource();
	    	dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
	    	dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
	    	dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));	 
	    	connection = dataSource.getConnection();
		    statement = connection.createStatement();
	    }
	    catch (SQLException e){
			e.printStackTrace();	
		}
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	    //end load connection details from properties file
/*
      try // try block to encompass any of the other three connection techniques - uncomment as needed
      {   
    	  // TECHNIQUE 2: Using initialization parameters from the Tomcat web.xml file
    	  // -------------------------------------------------------------------------
    	  //loading connection details from the web.xml file in Tomcat as init params
    	  
  /*
    	   Class.forName( config.getInitParameter( "databaseDriver" ) );
           connection = DriverManager.getConnection(config.getInitParameter( "databaseName" ), 
        		                                    config.getInitParameter( "username" ),
                                                    config.getInitParameter( "password" ) );
           statement = connection.createStatement();               
  */
    	  
    	  
    	  // TECHNIQUE 3: Using a dataSource object but no properties file - hard coded dataSource properties
    	  // ------------------------------------------------------------------------------------------------
    	  // loading the connection details into a dataSource object - but hardcoded except for the implicit driver load.
    	  
   /*
    	    MysqlDataSource dataSource = new MysqlDataSource();
    		dataSource.setUser("root");
    		dataSource.setPassword("rootMAC1$");
    		dataSource.setURL("jdbc:mysql://localhost:3306/colorsurvey?useTimezone=true&serverTimezone=UTC");
    		  
    		//establish a connection to the dataSource - i.e. the database
    		connection = dataSource.getConnection();
    	    //create a statement object
    		statement = connection.createStatement();
   */
    	  
    	  
           // TECHNIQUE 4: Classic static loading of the driver and connection parameters - This is the least preferred method
           //-----------------------------------------------------------------------------------------------------------------
           // classic static loading of the JDBC driver
    /*
	         Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/colorsurvey?useTimezone=true&serverTimezone=UTC", "root", "rootMAC1$" );
	         // create Statement to query database
	         statement = connection.createStatement();
    */
	    
/*
      } // end try  //end try block that encompasses any of the other three connection techniques.
      // for any exception throw an UnavailableException to 
      // indicate that the servlet is not currently available
      catch ( Exception exception ) 
      {
         exception.printStackTrace();
         throw new UnavailableException( exception.getMessage() );
      } // end catch
*/
   }  // end method init 

   // process survey response
   protected void doPost( HttpServletRequest request, HttpServletResponse response )
         throws ServletException, IOException
   {
      // set up response to client
      response.setContentType( "text/html" ); 
      PrintWriter out = response.getWriter();

      // read current survey response
      int value = Integer.parseInt( request.getParameter( "color" ) );
      String sql;
      // attempt to process a user vote and display current results
      try 
      {
         // update total for current survey response
         sql = "UPDATE surveyresults SET votes = votes + 1  WHERE id = " + value;
         statement.executeUpdate( sql );

         // get total of all survey responses
         sql = "SELECT sum( votes ) FROM surveyresults";
         ResultSet totalRS = statement.executeQuery( sql );
         totalRS.next(); // position to first record
         int total = totalRS.getInt( 1 );

         // get results
         sql = "SELECT surveyoption, votes, id FROM surveyresults ORDER BY id";
         ResultSet resultsRS = statement.executeQuery( sql );
         
         
         
             
               
         
         // start HTML document
         out.println( "<html>" );
         // begin head section of document
         out.println( "<head>" );  
         out.println( "<meta charset=\"utf-8\">" );
         out.println( "<style type='text/css'>");
         out.println( "<!-- ");
         out.println( "body{background-color:black; color:lime; font-family:calibri,sans-serif; font-size:1.3em; }");
         out.println( "h1 {text-align:center;} ");
         out.println( "h2 {font-size:1.1em;}");
         out.println( " .different {color:red; font-family: Calibri, sans-serif;}");
         out.println( " .moredifferent {color:#00BFFF; font-family:Calibri, sans-serif; text-decoration:underline; text-decoration-color:#00BFFF;}");
         out.println( " .finalnum {color:white;}");
         out.println( " -->");
         out.println( "</style>");
         out.println( "<title>Thank you!</title>" );
         out.println( "</head>" );
         // end head section of document
         
         // begin body section of document
         out.println( "<body>" ); 
		 out.println ("<h1>Thank you for participating in the CNT 4714 <span style='color:cyan'>C</span><span style='color:red'>O</span><span style='color:green'>L</span><span style='color:yellow'>O</span><span style='color:orange'>R</span> Preference Survey!!" );
		 out.println ("</b><br></h1>");
		 out.println ("<br /><h2 class=\"moredifferent\"> Current Results:</h2>" );
         out.println("<pre><span style='color:lightblue; font-family:Courier;'>");
         
         // process results
         int votes;
         
         while ( resultsRS.next() ) 
         {
            out.print( resultsRS.getString( 1 ) );
            out.print( ": " );
            votes = resultsRS.getInt( 2 );
            out.printf( "%.2f", ( double ) votes / total * 100 );
            out.print( "%\t  responses: " );
            out.println( votes );
         } // end while
         
                 
		 out.println();
		 out.println("</span>");
         out.print( "<span class=\"different\"> Total number of responses: <span class=\"finalnum\"> "+ total + "</span></span>" );
         out.println();
         out.println();
         resultsRS.close();
         
         
         sql = "select surveyoption, votes  from surveyresults where votes = (select max(votes) from surveyresults)";
         ResultSet favoriteRS = statement.executeQuery(sql);
         favoriteRS.next();
         out.print("<span class=\"different\">Currently the favorite color is ");
         out.print(favoriteRS.getString( 1 ) );
         out.println(", with a total of <span class=\"finalnum\">" + favoriteRS.getInt( 2 ) + "</span> votes.</span>");
       
         //favoriteRS.close();
         
         
                 
         // end HTML document
         out.println("</pre></body></html>");
         out.close();
      } // end try
      // if database exception occurs, return error page
      catch ( SQLException sqlException ) 
      {
         sqlException.printStackTrace();
         out.println( "<title>Error</title>" );
         out.println( "</head>" );  
         out.println( "<body><p>Database error occurred. " );
         out.println( "Try again later.</p></body></html>" );
         out.close();
      } // end catch
   } // end method doPost

   // close SQL statements and database when servlet terminates
   public void destroy()
   {
      // attempt to close statements and database connection
      try 
      {
         statement.close();
         connection.close();
      } // end try
      // handle database exceptions by returning error to client
      catch( SQLException sqlException ) 
      {
         sqlException.printStackTrace();
      } // end catch
   } // end method destroy
} // end class SurveyServlet

