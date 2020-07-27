package connection;
/*
 * coneção com bacno de dados
 */

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class SingleConnection {
	
	
	private static String url = "jdbc:postgresql://localhost:5432/sistema1";
	private static String password= "admin";
	private static String user = "postgres";
	private static Connection connection =null;
	
	
	static {
		conectar();
	}

	public SingleConnection() {
		
		conectar();
	}
	private static void conectar() {
		try {
		if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url,user,password);
				connection.setAutoCommit(false);
			}else {
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar");
		}
	}
	
	public static Connection getConnection() {
		return connection;
	 }
	
}
