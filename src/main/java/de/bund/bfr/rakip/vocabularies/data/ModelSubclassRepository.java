package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ModelClass;
import de.bund.bfr.rakip.vocabularies.domain.ModelSubclass;

public class ModelSubclassRepository implements BasicRepository<ModelSubclass> {

	private final Connection connection;
	private final ModelClassRepository modelClassRepository;

	public ModelSubclassRepository(Connection connection) {
		this.connection = connection;
		this.modelClassRepository = new ModelClassRepository(connection);
	}

	@Override
	public Optional<ModelSubclass> getById(int id) {
		
		String query = "SELECT * FROM model_subclass WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					int classId = resultSet.getInt("class_id");
					Optional<ModelClass> classCategory = modelClassRepository.getById(classId);

					if (classCategory.isPresent()) {
						return Optional.of(new ModelSubclass(id, name, classCategory.get()));
					}
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public ModelSubclass[] getAll() {
		ArrayList<ModelSubclass> subclasses = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM model_subclass");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int classId = resultSet.getInt("class_id");

				Optional<ModelClass> classCategory = modelClassRepository.getById(classId);
				if (!classCategory.isPresent())
					continue;

				subclasses.add(new ModelSubclass(id, name, classCategory.get()));
			}
		} catch (SQLException err) {
		}

		return subclasses.toArray(new ModelSubclass[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM model_subclass");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
