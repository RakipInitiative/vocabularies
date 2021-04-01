package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Format;

public class FormatRepository implements BasicRepository<Format> {

	private final Connection connection;

	public FormatRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Format> getById(int id) {

		String query = "SELECT * FROM format WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String comment = resultSet.getString("comment");

					return Optional.of(new Format(id, name, comment));
				}
			}

			return Optional.empty();

		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public Format[] getAll() {

		ArrayList<Format> formatList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM format")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");

				formatList.add(new Format(id, name, comment));
			}
		} catch (SQLException e) {
		}

		return formatList.toArray(new Format[0]);
	}
	
	@Override
	public String[] getAllNames() {

		ArrayList<String> names = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT name FROM format")) {

			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
		}

		return names.toArray(new String[0]);
	}	
}
