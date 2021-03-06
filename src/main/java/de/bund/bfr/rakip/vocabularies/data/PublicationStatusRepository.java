package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.PublicationStatus;

public class PublicationStatusRepository implements BasicRepository<PublicationStatus> {

	private final Connection connection;

	public PublicationStatusRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<PublicationStatus> getById(int id) {

		String query = "SELECT * FROM publication_status WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String comment = resultSet.getString("comment");

					return Optional.of(new PublicationStatus(id, name, comment));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public PublicationStatus[] getAll() {

		ArrayList<PublicationStatus> statusList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM publication_status")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");

				statusList.add(new PublicationStatus(id, name, comment));
			}
		} catch (SQLException err) {
		}

		return statusList.toArray(new PublicationStatus[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
    		 ResultSet resultSet = statement.executeQuery("SELECT name FROM publication_status")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
