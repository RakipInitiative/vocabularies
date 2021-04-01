package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ParameterSource;

import javax.xml.transform.Result;

public class ParameterSourceRepository implements BasicRepository<ParameterSource> {

	private final Connection connection;

	public ParameterSourceRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<ParameterSource> getById(int id) {

		String query = "SELECT * FROM parameter_source WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					return Optional.of(new ParameterSource(id, name));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public ParameterSource[] getAll() {

		ArrayList<ParameterSource> subjectList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM parameter_source");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				subjectList.add(new ParameterSource(id, name));
			}
		} catch (SQLException err) {
		}

		return subjectList.toArray(new ParameterSource[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM parameter_source");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
