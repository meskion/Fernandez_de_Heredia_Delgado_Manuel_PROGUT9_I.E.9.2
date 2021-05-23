package salesianas.conections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConection {
	/** Enlace a la base de datos*/
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sociosbaloncesto?autoReconnect=true&useSSL=false";
	/** Instancia de la conexion*/
	private static Connection instance = null;
	
	private DatabaseConection() {
	}
	/**
	 * Conexión con la base de datos
	 * @return 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "1234");
			instance = DriverManager.getConnection(JDBC_URL, props);
		}

		return instance;
	}

}
