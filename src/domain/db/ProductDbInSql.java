package domain.db;

import domain.model.Product;

import java.sql.*;
import java.util.*;

public class ProductDbInSql implements ProductDb {
	private Properties properties;
	private String url;

	public ProductDbInSql(Properties properties) {
		this.properties = properties;
		this.url = properties.getProperty("url");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new DbException(e);
		}
	}
	
	@Override
    public Product get(int id) throws DbException {
		if(id < 0){
			throw new DbException("Invalid productId");
		}
		//String query = "SELECT * FROM person WHERE userid = 'personId'";
		String query = "SELECT * FROM product WHERE productid = ?";
		Product product = null;
		try (Connection connection = DriverManager.getConnection(url,properties);
             PreparedStatement statement = connection.prepareStatement(query)) {

		    statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while(result.next()) {
				int productIdP = Integer.parseInt(result.getString("productid"));
				String name = result.getString("name");
				String description = result.getString("description");
				Double price = Double.parseDouble(result.getString("price"));
				product = (makeProduct(id, name, description, price));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return product;
	}
	
	@Override
    public List<Product> getAll(){
		List<Product> products = new ArrayList<>();
		String query = "SELECT * FROM product";

		try (Connection connection = DriverManager.getConnection(url,properties);
			PreparedStatement statement = connection.prepareStatement(query)) {

			ResultSet result = statement.executeQuery();

			while(result.next()){
				int productId = Integer.parseInt(result.getString("productid"));
				String name = result.getString("name");
				String description = result.getString("description");
				Double price = Double.parseDouble(result.getString("price"));
				products.add(makeProduct(productId, name, description, price));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return products;
	}

	@Override
    public void add(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		if (this.getAll().contains(product)) {
			throw new DbException("Product already exists");
		}
		//String query = "insert into person(userid, email, password, firstname, lastname) values(\'" + person.getUserid() + "\', \'" + person.getEmail() +
		//		"\', \'" + person.getPassword() + "\', \'" + person.getFirstName() + "\', \'" + person.getLastName() + "\')";
        String query = "insert into product(productid, name, description, price) values(?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(url,properties);
			 PreparedStatement statement = connection.prepareStatement(query)) {

			int id = 1;
			product.setProductId(id);
			while(this.getAll().contains(product)) {
				id++;
                product.setProductId(id);
			}


			statement.setInt(1, product.getProductId());
			statement.setString(2, product.getName());
			statement.setString(3, product.getDescription());
			statement.setDouble(4, product.getPrice());

			statement.execute();

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

	}
	
	@Override
    public void update(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		if(!this.getAll().contains(product)){
			throw new DbException("No product found");
		}
		/*String query = "update person set email = '" + person.getEmail() +
				"', password = '" + person.getPassword() +
				"', firstname = '" + person.getFirstName() +
				"', lastname = '" + person.getLastName() + "'  where userid='" + person.getUserid() + "'";*/
		String query = "update product set name = ?, description = ?, price = ? where productid = ?";
		try (Connection connection = DriverManager.getConnection(url,properties);
			 PreparedStatement statement = connection.prepareStatement(query)) {

		    statement.setString(1, product.getName());
		    statement.setString(2, product.getDescription());
		    statement.setDouble(3, product.getPrice());
		    statement.setInt(4, product.getProductId());


			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}
	
	@Override
    public void delete(int id){
		if(id < 0){
			throw new DbException("Not a valid productId");
		}
		//String query = "delete from person where userid='" + personId + "'";
		String query = "delete from product where productid = ?";
		try (Connection connection = DriverManager.getConnection(url,properties);
			 PreparedStatement statement = connection.prepareStatement(query)) {

		    statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
    public int getNumberOfProducts() {
		return this.getAll().size();
	}

	private Product makeProduct(int productId, String name, String desc, double price) {
		try {
			Product product = new Product(productId, name, desc, price);
			return (product);
		}
		catch (IllegalArgumentException e) {
			throw new DbException(e.getMessage(), e);
		}
	}
}
