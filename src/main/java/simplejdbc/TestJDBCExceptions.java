package simplejdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJDBCExceptions {

	public static void main(String[] args) {
            Connection connection = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                // Pré-chargement du driver, ne devrait pas âtre nécessaire avec des versions plus récentes           
                // Class.forName("org.apache.derby.jdbc.ClientDriver");                  
                connection = getConnectionWithDriverManager();
                stmt = connection.createStatement();
                rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
                while (rs.next()) { // Tant qu'il reste des enregistrements
                    // On récupère les champs de l'enregistrement courant
                    int id = rs.getInt("CUSTOMER_ID");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    // On fait quelque chose avec l'enregistrement courant
                    System.out.printf("Client %d (%s), email : %s %n", id, name, email);
                }
            } catch (SQLException ex) {
                Logger.getLogger("JDBC").log(Level.SEVERE, null, ex);
	    } finally { // Exécuté qu'il y ait des exceptions ou non
                // On ferme tout
                if (rs != null)
                    try {
                        rs.close();
                    } catch (SQLException e) { /* ?? */ }
                if (stmt != null)
                    try {
                        stmt.close();
                    } catch (SQLException e) { /* ?? */ }
                if (connection != null)
                    try {
                        connection.close();
                    } catch (SQLException e) { /* ?? */ }                        
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
