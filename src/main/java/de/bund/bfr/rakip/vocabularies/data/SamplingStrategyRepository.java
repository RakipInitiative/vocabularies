package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.SamplingStrategy;

public class SamplingStrategyRepository implements BasicRepository<SamplingStrategy> {

	private final Connection connection;

	public SamplingStrategyRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<SamplingStrategy> getById(int id) {

		String query = "SELECT * FROM sampling_strategy WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String comment = resultSet.getString("comment");
					return Optional.of(new SamplingStrategy(id, name, comment));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public SamplingStrategy[] getAll() {

		ArrayList<SamplingStrategy> strategies = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM sampling_strategy");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");

				strategies.add(new SamplingStrategy(id, name, comment));
			}
		} catch (SQLException err) {
		}

		return strategies.toArray(new SamplingStrategy[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM sampling_strategy");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
