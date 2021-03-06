package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.IndSum;

public class IndSumRepository implements BasicRepository<IndSum> {

	private final Connection connection;

	public IndSumRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<IndSum> getById(int id) {

		String query = "SELECT * FROM ind_sum WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String ssd = resultSet.getString("ssd");

					return Optional.of(new IndSum(id, name, ssd));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public IndSum[] getAll() {

		ArrayList<IndSum> sums = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ind_sum")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("ssd");

				sums.add(new IndSum(id, name, ssd));
			}
		} catch (SQLException err) {
		}

		return sums.toArray(new IndSum[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT name FROM ind_sum")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
