package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Country;

public class CountryRepository implements BasicRepository<Country> {

	private final Connection connection;

	public CountryRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Country> getById(int id) {

		String query = "SELECT * FROM country WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String iso = resultSet.getString("iso");
					return Optional.of(new Country(id, name, iso));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public Country[] getAll() {

		ArrayList<Country> countryList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM country")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String iso = resultSet.getString("iso");

				countryList.add(new Country(id, name, iso));
			}

		} catch (SQLException e) {
		}

		return countryList.toArray(new Country[0]);
	}
	
	@Override
	public String[] getAllNames() {

		ArrayList<String> names = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM country")) {

			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
			}

		} catch (SQLException e) {
		}

		return names.toArray(new String[0]);
	}
}
