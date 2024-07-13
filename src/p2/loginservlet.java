package p2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public loginservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "vrutanshi");

			String Username = request.getParameter("uname").trim();
			String Password = request.getParameter("password").trim();

			
			// Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(
					"select * from register where uname='" + Username + "' and password='" + Password + "'");
			//
			// ps.setString(1, Username);
			// ps.setString(2, Password);

			// String query = "select * from UserRegistration where
			// username='"+Username+"' and password='"+Password+"'";
			
		

			Boolean rs = ps.execute();

			if (rs) {
				out.print("<script>alert('Login Successfully...');</script>");
				out.print("<script>localStorage.setItem('userID', '" + Username + "');</script>");
				out.print("<script>window.onload = function() { window.location.href = 'navbar.html';}</script>");
				 response.sendRedirect("./navbar.html");
				 

				// out.print("<script>alert('Login Successfully');</script>");
				// HttpSession session = request.getSession(true);

				// Cookie ck = new Cookie("Username", Username);
				// response.addCookie(ck);

				// session.setAttribute("Username", Username);
				// response.sendRedirect(request.getContextPath() +
				// "/SessioData");
				
				ps.close();
				con.close();
				// response.sendRedirect("Main.html");
				//
				// rd = request.getRequestDispatcher("./Main.html");
				// rd.include(request, response);
			} else {
				out.print("<script>alert('Please Enter Valid Username and Password');</script>");
				rd = request.getRequestDispatcher("./login.html");
				rd.include(request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
