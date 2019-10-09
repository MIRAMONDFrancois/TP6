package testingwithhsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DAO {
	private final DataSource myDataSource;
	
	public DAO(DataSource dataSource) {
		myDataSource = dataSource;
	}

	/**
	 * Renvoie le nom d'un client à partir de son ID
	 * @param id la clé du client à chercher
	 * @return le nom du client (LastName) ou null si pas trouvé
	 * @throws SQLException 
	 */
	public String nameOfCustomer(int id) throws SQLException {
		String result = null;
		
		String sql = "SELECT LastName FROM Customer WHERE ID = ?";
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql)) {
			statement.setInt(1, id); // On fixe le 1° paramètre de la requête
			try ( ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// est-ce qu'il y a un résultat ? (pas besoin de "while", 
                                        // il y a au plus un enregistrement)
					// On récupère les champs de l'enregistrement courant
					result = resultSet.getString("LastName");
				}
			}
		}
		// dernière ligne : on renvoie le résultat
		return result;
	}
        
        
        public int addProduct(int productId, String name, int prix) throws SQLException {
            int result = 0;
            String sql = "INSERT INTO PRODUCT (ID, Name, Price) VALUES (?, ?, ?)";
		try (Connection myConnection = myDataSource.getConnection(); 
                    PreparedStatement statement = myConnection.prepareStatement(sql)) {
                    statement.setInt(1, productId); // On fixe le 1° paramètre de la requête
                    statement.setString(2, name); // On fixe le 1° paramètre de la requête
                    statement.setInt(3, prix); // On fixe le 1° paramètre de la requête
                    result = statement.executeUpdate();
		}
                
            return result;
        }
        
        
        public ProductEntity getProduct(int productId) throws SQLException {
            ProductEntity item = null;
            String sql = "SELECT (Name, Price) FROM PRODUCT WHERE ID = ?";
		try (Connection myConnection = myDataSource.getConnection(); 
                    PreparedStatement statement = myConnection.prepareStatement(sql)) {
                    statement.setInt(1, productId); // On fixe le 1° paramètre de la requête
                    ProductEntity item = new ProductEntity();
		}
                
            return result;
        }
	
}
