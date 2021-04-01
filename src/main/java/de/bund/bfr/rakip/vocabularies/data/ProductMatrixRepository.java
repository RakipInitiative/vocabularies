package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ProductMatrix;

public class ProductMatrixRepository implements BasicRepository<ProductMatrix> {

	private final Connection connection;

	public ProductMatrixRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<ProductMatrix> getById(int id) {

		String query = "SELECT * FROM product_matrix WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String ssd = resultSet.getString("ssd");
					String name = resultSet.getString("name");
					String comment = resultSet.getString("comment");

					return Optional.of(new ProductMatrix(id, ssd, name, comment));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public ProductMatrix[] getAll() {
		ArrayList<ProductMatrix> matrixList = new ArrayList<>();
		
		try {			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM product_matrix");
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String ssd = resultSet.getString("ssd");
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");
				
				matrixList.add(new ProductMatrix(id, ssd, name, comment));
			}
		} catch (SQLException err) {}

		return matrixList.toArray(new ProductMatrix[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM product_matrix");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
