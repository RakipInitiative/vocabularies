package de.bund.bfr.rakip.vocabularies.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.CollectionTool;

public class CollectionToolRepository implements BasicRepository<CollectionTool> {

	private final Connection connection;

	public CollectionToolRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<CollectionTool> getById(int id) {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM collection_tool WHERE id = '" + id + "'");

			if (resultSet.next()) {
				String name = resultSet.getString("name");
				return Optional.of(new CollectionTool(id, name));
			}
			return Optional.empty();
		} catch (SQLException e) {
			return Optional.empty();
		}
	}

	@Override
	public CollectionTool[] getAll() {

		ArrayList<CollectionTool> tools = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM collection_tool");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				tools.add(new CollectionTool(id, name));
			}
		} catch (SQLException e) {
		}

		return tools.toArray(new CollectionTool[0]);
	}
	
	@Override
	public String[] getAllNames() {
		ArrayList<String> names = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT name FROM collection_tool");

			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
		}

		return names.toArray(new String[0]);
	}
}
