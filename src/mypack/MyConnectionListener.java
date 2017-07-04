package mypack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyConnectionListener
 *
 */
@WebListener
public class MyConnectionListener implements ServletContextListener {
	Connection con=null;

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}       
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent e)  { 
    	
		String driver=e.getServletContext().getInitParameter("driverclass");
		String jdbcurl=e.getServletContext().getInitParameter("jdbcurl");
		String user=e.getServletContext().getInitParameter("user");
		String password=e.getServletContext().getInitParameter("password");
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(jdbcurl,user,password);
			
			e.getServletContext().setAttribute("jdbccon",con);
			System.out.println("'con' is set as context level attribute");
		}
		catch (ClassNotFoundException | SQLException e1) {
			
			e1.printStackTrace();
		}
    }
	
}
