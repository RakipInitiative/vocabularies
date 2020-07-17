package de.bund.bfr.rakip.vocabularies.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ParameterDatatype;

public class ParameterDatatypeRepository implements BasicRepository<ParameterDatatype> {
	private final Connection connection;

	public ParameterDatatypeRepository(Connection connection) {
	        this.connection = connection;
	    }

	@Override
	public Optional<ParameterDatatype> getById(int id) {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM parameter_datatype WHERE id = " + id);

			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");
				String representedAs = resultSet.getString("represented_as");

				return Optional.of(new ParameterDatatype(id, name, comment, representedAs));
			}

			return Optional.empty();
		} catch (SQLException e) {
			return Optional.empty();
		}
	}

	@Override
	public ParameterDatatype[] getAll() {

		ArrayList<ParameterDatatype> availabilityList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM parameter_datatype");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String comment = resultSet.getString("comment");
				String representedAs = resultSet.getString("represented_as");

				availabilityList.add(new ParameterDatatype(id, name, comment, representedAs));
			}
		} catch (SQLException e) {
		}

		return availabilityList.toArray(new ParameterDatatype[0]);
	}

	@Override
	public String[] getAllNames() {
		ArrayList<String> names = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT name FROM parameter_datatype");

			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
		}

		return names.toArray(new String[0]);
	}
}
