package web.application.project.testDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testDBServlet
 */
@WebServlet("/testDBServlet")
public class testDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//setup connection variables
		String username="springstudent";
		String password="springstudent";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		
		String driver="com.mysql.cj.jdbc.Driver";
		
		//get connection to database
		
		try {
			
			PrintWriter out =response.getWriter();
			
			System.out.println("Connecting to database !");
			
			Class.forName(driver);
			
			Connection myConnection=DriverManager.getConnection(jdbcUrl,username,password);
			
			System.out.println("!!!!!SUCCESS!!!!!");
			
			myConnection.close();
			
		}catch (Exception e) {
			System.out.println("!!!!!Failed!!!!!");
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	

}
