package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ParameterDistribution;

public class ParameterDistributionRepository implements BasicRepository<ParameterDistribution> {

	private final Connection connection;

	public ParameterDistributionRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<ParameterDistribution> getById(int id) {

		String query = "SELECT * FROM parameter_distribution WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String comment = resultSet.getString("comment");

					return Optional.of(new ParameterDistribution(id, name, comment));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public ParameterDistribution[] getAll() {

		ArrayList<ParameterDistribution> distributions = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM parameter_distribution")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");

				distributions.add(new ParameterDistribution(id, name, comment));
			}
		} catch (SQLException err) {
		}

		return distributions.toArray(new ParameterDistribution[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
    		 ResultSet resultSet = statement.executeQuery("SELECT name FROM parameter_distribution")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
