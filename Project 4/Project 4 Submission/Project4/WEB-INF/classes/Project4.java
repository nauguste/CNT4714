/*  Name:  Naseem Auguste
     Course: CNT 4714 – Fall 2022 
     Assignment title: Project 3 – Two-Tier Client-Server Application Development With MySQL and JDBC
     Date: Wednesday November 2, 2022 
*/ 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.MysqlDataSource;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
	
public class Project4  extends HttpServlet 
{
	// process "get" requests from clients  
	
	private Connection connection;
	private Statement statement;
	private int returnVal;
	private int[] updateReturns;
	
	private void getDBConnection()
	{
		/*
		Properties properties = new Properties();
		FileInputStream filein = null;
		MysqlDataSource dataSource = null;
		try
		{
		filein = new FileInputStream("root.properties");
		properties.load(filein);
		
		dataSource = new MysqlDataSource();
	    dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
	    dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
	    dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
	    
	    connection = dataSource.getConnection();
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		*/
	}
		@Override
		public void init(ServletConfig config) throws ServletException {
		try {
			// using xml file
			Class.forName(config.getInitParameter("databaseDriver"));
			connection = DriverManager.getConnection(config.getInitParameter("databaseName"),config.getInitParameter("username"), config.getInitParameter("password"));
			statement = connection.createStatement();
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new UnavailableException(e.getMessage());
		}
	}
	
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  
	{
		
		String SQLQuery = request.getParameter("SQLQuery");
		String Query = SQLQuery.toLowerCase();
		String result = null;
		
		if(Query.contains("select")) 
		{
			try 
			{
				result = doSelect(Query);
			} 
			catch (IllegalStateException e1) 
			{
				 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				 e1.printStackTrace();
			} 
			catch (SQLException e1) 
			{
				 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				 e1.printStackTrace();
			}
		}
		else
		{
			try 
			{	
				result = doUpdate(Query);
			} 
			catch (IllegalStateException e1) 
			{
				 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				 e1.printStackTrace();
			} 
			catch (SQLException e1) 
			{
				 JOptionPane.showMessageDialog( null, e1.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				 e1.printStackTrace();
			}
			
		}
	
	    HttpSession session = request.getSession();
	    session.setAttribute("message",  result);;
	    session.setAttribute("SQLQUery",SQLQuery);
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome - rootHome.jsp");
	    dispatcher.forward(request, response);      
	    
		
		
	
	}
	public String doSelect(String SQLQuery) throws SQLException {
		String res;
		ResultSet table = statement.executeQuery(SQLQuery);

		ResultSetMetaData metaData = table.getMetaData();
		
		int numOfColumns = metaData.getColumnCount();
		
		String tableHead = "<div class='container-fluid'><div class='row justify-content-center'><div class='table-responsive-sm-10 table-responsive-md-10 table-responsive-lg-10'><table class='table'>";
		String tableColumns = "<thead class='thead-dark'><tr>";
		for (int i = 1; i <= numOfColumns; i++) 
		{
			tableColumns += "<th scope='col'>" + metaData.getColumnName(i) + "</th>";
		}

		tableColumns += "</tr></thead>";


		String tableBodyHTML = "<tbody>";
		
		while (table.next()) 
		{
			tableBodyHTML += "<tr>";
			for (int i = 1; i <= numOfColumns; i++) 
			{
				if (i == 1)
					tableBodyHTML += "<td scope'row'>" + table.getString(i) + "</th>";
				else
					tableBodyHTML += "<td>" + table.getString(i) + "</th>";
			}
			tableBodyHTML += "</tr>";
		}

		tableBodyHTML += "</tbody>";

		String tableClosingHTML = "</table></div></div></div>";
		res = tableHead + tableColumns + tableBodyHTML + tableClosingHTML;

		return res;
	}
	
	public String doUpdate(String Query) throws SQLException 
	{
		String res = null;
		int numRows = 0;
		
		ResultSet before = statement.executeQuery("select COUNT(*) from shipments where quantity >= 100");
		before.next();
		int shipsB = before.getInt(1);
		
		
		statement.executeUpdate("create table shipmentsBeforeUpdate like shipments");
	
		statement.executeUpdate("insert into shipmentsBeforeUpdate select * from shipments");
		
		
		numRows = statement.executeUpdate(Query);
		res = "<div> The statement executed succesfully.</div><div>" + numRows + " row(s) affected</div>";
		
		ResultSet afterQuantityCheck = statement.executeQuery("select COUNT(*) from shipments where quantity >= 100");
		afterQuantityCheck.next();
		int shipsA = afterQuantityCheck.getInt(1);
		
		res += "<div>" + shipsB + " < " + shipsA + "</div>";
		
		if(shipsB < shipsA) 
		{
			int numberOfRowsAffectedAfterIncrementBy5 = statement.executeUpdate("update suppliers set status = status + 5 where snum in ( select distinct snum from shipments left join shipmentsBeforeUpdate using (snum, pnum, jnum, quantity) where shipmentsBeforeUpdate.snum is null)");
			res += "<div>Business Logic Detected! - Updating Supplier Status</div>";
			res += "<div>Business Logic Updated " + numberOfRowsAffectedAfterIncrementBy5 + " Supplier(s) status marks</div>";
		}
		
		statement.executeUpdate("drop table shipmentsBeforeUpdate");
		
		return res;
	}

}
