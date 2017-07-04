package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	Connection con=null;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		String id=request.getParameter("uid");
		String pwd=request.getParameter("pwd");
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			 ps=con.prepareStatement("select * from users where uid=?");
			ps.setString(1,id);
			rs=ps.executeQuery();
			boolean flag=false;
			while(rs.next())
			{
				if(rs.getString(2).equals(pwd))
				{
					flag=true;
					User user=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					HttpSession session=request.getSession();
					session.setAttribute("userinfo",user);
					break;
				}
				
			}
			if(flag==true)
			{
				Cookie [] allcookies=request.getCookies();
				if(allcookies != null)
				{
					for(Cookie c: allcookies)
					{
						if(c.getName().equals("loginerror"))
						{
							c.setMaxAge(0);
							response.addCookie(c);
						}
					}
				}
				
				RequestDispatcher rd=request.getRequestDispatcher("/InboxServlet");
				rd.forward(request, response);
			}
			else
			{
				
				Cookie c=new Cookie("loginerror","Wrong ID/pwd");
				response.addCookie(c);
				/*RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);*/
				response.sendRedirect("/myCookies/login.jsp");
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
