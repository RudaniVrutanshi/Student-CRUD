package p2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.WhileStatement;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "vrutanshi");
		PreparedStatement ps = conn.prepareStatement("insert into register values(?,?,?)");

		String username = request.getParameter("uname");
		//int marks = Integer.parseInt(request.getParameter("marks"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		ps.setString(1, username);
		ps.setString(2, email);
		ps.setString(3, password);

		int i = ps.executeUpdate();
		if(i > 0)
		{
		out.print("<script>alert('Add Successfull');</script>");	
//		rd = request.getRequestDispatcher("./ShowAllData");
//		rd.include(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		rd.forward(request, response);
		  // response.sendRedirect("./ShowAllData");
		}
		else
		{
		out.print("<script>alert('Please try again');</script>");
		}
		}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
