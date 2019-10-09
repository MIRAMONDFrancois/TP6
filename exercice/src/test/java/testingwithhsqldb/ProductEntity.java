
package testingwithhsqldb;

/**
 * Correspond à un enregistrement de la table Customer
 */
public class ProductEntity {
	// TODO : ajouter les autres propriétés
	private int productId;
	private String name;
	private String price;

	public ProductEntity(int productId, String name, String price) {
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	/**
	 * Get the value of customerId
	 *
	 * @return the value of customerId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the value of addressLine1
	 *
	 * @return the value of addressLine1
	 */
	public String getPrice() {
		return price;
	}


}
