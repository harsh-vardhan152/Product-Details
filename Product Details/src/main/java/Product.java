import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Product() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		out.print("<h1>Display of the Product acccording to the id entered</h1>");
		out.print("<table border ='1'><tr><th>id</th><th>Name</th><th>Company</th><th>Quantity</th><th>Price</th>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productdetails", "root",
					"Harsh@123");
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from details where id=" + id + "");
			while (rs.next()) {
				out.print("<tr><td>");
				out.println(rs.getInt(1));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString(2));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString(3));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getInt(4));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getFloat(5));
				out.print("</td>");
				out.print("</tr>");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
