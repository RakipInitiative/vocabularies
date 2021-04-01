package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.SamplingMethod;

public class SamplingMethodRepository implements BasicRepository<SamplingMethod> {

	private final Connection connection;

	public SamplingMethodRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<SamplingMethod> getById(int id) {

		String query = "SELECT * FROM sampling_method WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String sampmd = resultSet.getString("sampmd");
					String comment = resultSet.getString("comment");

					return Optional.of(new SamplingMethod(id, name, sampmd, comment));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public SamplingMethod[] getAll() {

		ArrayList<SamplingMethod> methodList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM sampling_method")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String sampmd = resultSet.getString("sampmd");
				String comment = resultSet.getString("comment");

				methodList.add(new SamplingMethod(id, name, sampmd, comment));
			}
		} catch (SQLException err) {
		}

		return methodList.toArray(new SamplingMethod[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT name FROM sampling_method")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
