package simplejdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJDBCTryWithResources {

	public static void main(String[] args) throws Exception {
           // Pré-chargement du driver, ne devrait pas âtre nécessaire avec des versions plus récentes           
           Class.forName("org.apache.derby.jdbc.ClientDriver");  
            try (   // Les ressources qui doivent être fermées automatiquement
                    Connection connection = getConnectionWithDriverManager();
                    Statement stmt = connection.createStatement(); 
                    ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER")
            ) {
                while (rs.next()) { 
                    int id = rs.getInt("CUSTOMER_ID");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    System.out.printf("Client %d (%s), email : %s %n", id, name, email);
                }
            }  catch (SQLException ex) {
                Logger.getLogger("JDBC").log(Level.SEVERE, null, ex);
	    }
	}

	public static Connection getConnectionWithDataSource() throws SQLException {
		return DataSourceFactory.getDataSource().getConnection();
	}

	public static Connection getConnectionWithDriverManager() throws SQLException {
		String URL = "jdbc:derby://localhost:1527/sample";
		String USERNAME = "app";
		String PASSWORD = "app";
		// On se connecte au serveur
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);	
	}
	
}
