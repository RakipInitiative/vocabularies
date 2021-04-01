package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Population;

public class PopulationRepository implements BasicRepository<Population> {

	private final Connection connection;

	public PopulationRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Population> getById(int id) {

		String query = "SELECT * FROM population WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String foodon = resultSet.getString("foodon");
					return Optional.of(new Population(id, name, foodon));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public Population[] getAll() {
		ArrayList<Population> populationList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM population")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String foodon = resultSet.getString("foodon");

				populationList.add(new Population(id, name, foodon));
			}			
		} catch (SQLException err) {
		}

		return populationList.toArray(new Population[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
    		 ResultSet resultSet = statement.executeQuery("SELECT name FROM population")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
