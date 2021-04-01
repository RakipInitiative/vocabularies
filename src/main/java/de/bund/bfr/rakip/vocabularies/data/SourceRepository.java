package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Source;

public class SourceRepository implements BasicRepository<Source> {

	private final Connection connection;

	public SourceRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Source> getById(int id) {
		
		String query = "SELECT * FROM sources WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String comment = resultSet.getString("comment");

					return Optional.of(new Source(id, name, comment));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public Source[] getAll() {

		ArrayList<Source> sourceList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM sources");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");

				sourceList.add(new Source(id, name, comment));
			}
		} catch (SQLException err) {
		}

		return sourceList.toArray(new Source[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM sources");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
