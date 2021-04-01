package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.AccreditationProcedure;

public class AccreditationProcedureRepository implements BasicRepository<AccreditationProcedure> {
	private final Connection connection;

	public AccreditationProcedureRepository(Connection connection) {
	        this.connection = connection;
	    }

	@Override
	public Optional<AccreditationProcedure> getById(int id) {
		String query = "SELECT * FROM accreditation_procedure WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String mdstat = resultSet.getString("mdstat");
					String name = resultSet.getString("name");

					return Optional.of(new AccreditationProcedure(id, mdstat, name));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public AccreditationProcedure[] getAll() {

		ArrayList<AccreditationProcedure> availabilityList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM accreditation_procedure");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String mdstat = resultSet.getString("mdstat");
				String name = resultSet.getString("name");

				availabilityList.add(new AccreditationProcedure(id, mdstat, name));
			}
		} catch (SQLException e) {
		}

		return availabilityList.toArray(new AccreditationProcedure[0]);
	}

	@Override
	public String[] getAllNames() {
		ArrayList<String> names = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT name FROM accreditation_procedure");

			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
		}

		return names.toArray(new String[0]);
	}
}
