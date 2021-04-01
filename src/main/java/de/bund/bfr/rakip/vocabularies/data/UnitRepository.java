package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Unit;
import de.bund.bfr.rakip.vocabularies.domain.UnitCategory;

import javax.xml.transform.Result;

public class UnitRepository implements BasicRepository<Unit> {

	private final Connection connection;
	private final UnitCategoryRepository categoryRepository;

	public UnitRepository(Connection connection) {
		this.connection = connection;
		this.categoryRepository = new UnitCategoryRepository(connection);
	}

	@Override
	public Optional<Unit> getById(int id) {

		String query = "SELECT * FROM unit WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String ssd = resultSet.getString("ssd");
					String comment = resultSet.getString("comment");
					int categoryId = resultSet.getInt("category_id");

					Optional<UnitCategory> category = categoryRepository.getById(categoryId);
					if (category.isPresent()) {
						return Optional.of(new Unit(id, name, ssd, comment, category.get()));
					}
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public Unit[] getAll() {
		ArrayList<Unit> units = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM unit");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("ssd");
				String comment = resultSet.getString("comment");
				int categoryId = resultSet.getInt("category_id");
				Optional<UnitCategory> category = categoryRepository.getById(categoryId);
				if (!category.isPresent())
					continue;

				units.add(new Unit(id, name, ssd, comment, category.get()));
			}
		} catch (SQLException err) {
		}

		return units.toArray(new Unit[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM unit");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
