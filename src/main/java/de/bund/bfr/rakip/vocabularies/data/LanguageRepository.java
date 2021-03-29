package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Language;

public class LanguageRepository implements BasicRepository<Language> {

	private final Connection connection;

	public LanguageRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Language> getById(int id) {
		String query = "SELECT * FROM language WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String code = resultSet.getString("code");
				String name = resultSet.getString("name");
				return Optional.of(new Language(id, code, name));
			}
			return Optional.empty();

		}
		catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public Language[] getAll() {

		ArrayList<Language> languageList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM language");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String code = resultSet.getString("code");
				String name = resultSet.getString("name");

				languageList.add(new Language(id, code, name));
			}
		} catch (SQLException err) {
		}

		return languageList.toArray(new Language[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM language");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
