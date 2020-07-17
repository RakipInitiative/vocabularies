package de.bund.bfr.rakip.vocabularies.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ParameterClassification;

public class ParameterClassificationRepository implements BasicRepository<ParameterClassification> {
	private final Connection connection;

	public ParameterClassificationRepository(Connection connection) {
	        this.connection = connection;
	    }

	@Override
	public Optional<ParameterClassification> getById(int id) {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM parameter_classification WHERE id = " + id);

			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");

				return Optional.of(new ParameterClassification(id, name, comment));
			}

			return Optional.empty();
		} catch (SQLException e) {
			return Optional.empty();
		}
	}

	@Override
	public ParameterClassification[] getAll() {

		ArrayList<ParameterClassification> availabilityList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM parameter_classification");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");

				availabilityList.add(new ParameterClassification(id, name, comment));
			}
		} catch (SQLException e) {
		}

		return availabilityList.toArray(new ParameterClassification[0]);
	}

	@Override
	public String[] getAllNames() {
		ArrayList<String> names = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT name FROM parameter_classification");

			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
		}

		return names.toArray(new String[0]);
	}
}
