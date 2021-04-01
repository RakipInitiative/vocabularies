package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ModelClass;

public class ModelClassRepository implements BasicRepository<ModelClass> {

	private final Connection connection;

	public ModelClassRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<ModelClass> getById(int id) {
		
		String query = "SELECT * FROM model_class WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					return Optional.of(new ModelClass(id, name));
				}
				return Optional.empty();
			}
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
    public ModelClass[] getAll() {
    	
		ArrayList<ModelClass> classes = new ArrayList<>();

		try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM model_class");
	        
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");

	            classes.add(new ModelClass(id, name));
	        }
    	} catch (SQLException err) {
    	}

        return classes.toArray(new ModelClass[0]);
    }
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM model_class");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
