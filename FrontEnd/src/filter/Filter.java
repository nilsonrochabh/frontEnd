package filter;

import java.io.IOException;
import java.sql.*;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;



import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

import connection.SingleConnection;
 

@WebFilter(urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter {

	private static Connection connection = SingleConnection.getConnection();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub


	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		try {
			arg2.doFilter(arg0, arg1);
			connection.commit();

		} catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		try {
			 connection = SingleConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} 
	

}
