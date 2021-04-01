package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Packaging;

public class PackagingRepository implements BasicRepository<Packaging> {

	private final Connection connection;

	public PackagingRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Packaging> getById(int id) {

		String query = "SELECT * FROM packaging WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String ssd = resultSet.getString("ssd");
					String comment = resultSet.getString("comment");

					return Optional.of(new Packaging(id, name, ssd, comment));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public Packaging[] getAll() {
		ArrayList<Packaging> packagingList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM packaging");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("ssd");
				String comment = resultSet.getString("comment");

				packagingList.add(new Packaging(id, name, ssd, comment));
			}
		} catch (SQLException err) {
		}

		return packagingList.toArray(new Packaging[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM packaging");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
