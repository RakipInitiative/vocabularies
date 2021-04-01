package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ProductionMethod;

public class ProductionMethodRepository implements BasicRepository<ProductionMethod> {

	private final Connection connection;

	public ProductionMethodRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<ProductionMethod> getById(int id) {

		String query = "SELECT * FROM prodmeth WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String ssd = resultSet.getString("ssd");
					String comment = resultSet.getString("comment");

					return Optional.of(new ProductionMethod(id, name, ssd, comment));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public ProductionMethod[] getAll() {

		ArrayList<ProductionMethod> methodList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM prodmeth");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("ssd");
				String comment = resultSet.getString("comment");

				methodList.add(new ProductionMethod(id, name, ssd, comment));
			}
		} catch (SQLException err) {
		}

		return methodList.toArray(new ProductionMethod[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM prodmeth");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
