package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.LanguageWrittenIn;

public class LanguageWrittenInRepository implements BasicRepository<LanguageWrittenIn> {

	private final Connection connection;

	public LanguageWrittenInRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<LanguageWrittenIn> getById(int id) {

		String query = "SELECT * FROM language_written_in WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					return Optional.of(new LanguageWrittenIn(id, name));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public LanguageWrittenIn[] getAll() {
		ArrayList<LanguageWrittenIn> languageList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM language_written_in")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				languageList.add(new LanguageWrittenIn(id, name));
			}
		} catch (SQLException err) {
		}

		return languageList.toArray(new LanguageWrittenIn[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT name FROM language_written_in")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
