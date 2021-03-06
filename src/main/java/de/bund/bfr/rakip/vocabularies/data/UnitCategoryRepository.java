package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.UnitCategory;

import javax.xml.transform.Result;

public class UnitCategoryRepository implements BasicRepository<UnitCategory> {

	private final Connection connection;

	public UnitCategoryRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<UnitCategory> getById(int id) {

		String query = "SELECT * FROM unit_category WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					return Optional.of(new UnitCategory(id, name));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public UnitCategory[] getAll() {

		ArrayList<UnitCategory> categoryList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM unit_category")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				categoryList.add(new UnitCategory(id, name));
			}
		} catch (SQLException err) {
		}

		return categoryList.toArray(new UnitCategory[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
    		 ResultSet resultSet = statement.executeQuery("SELECT name FROM unit_category")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
