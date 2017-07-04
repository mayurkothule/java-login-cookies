package mypack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		int val=0;
		Cookie []allcookies=request.getCookies();
		if(allcookies!=null)
		{
			for(Cookie c:allcookies)
			{
				if(c.getName().equals("visitcount"))
				{
					val=Integer.parseInt(c.getValue());
				}
			}
		}
		val++;
		Cookie c=new Cookie("visitcount",""+val);
		response.addCookie(c);
		out.print("Visit Count:"+val);
		out.print("<a href='http://localhost:8080/myCookies/CookieServlet'>REFRESH</a>");
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
