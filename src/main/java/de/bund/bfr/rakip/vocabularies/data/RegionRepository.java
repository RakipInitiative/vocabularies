package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Region;

public class RegionRepository implements BasicRepository<Region> {

	private final Connection connection;

	public RegionRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Region> getById(int id) {

		String query = "SELECT * FROM region WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String ssd = resultSet.getString("nuts");

					return Optional.of(new Region(id, name, ssd));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public Region[] getAll() {

		ArrayList<Region> areaList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM region")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("nuts");

				areaList.add(new Region(id, name, ssd));
			}
		} catch (SQLException err) {
		}

		return areaList.toArray(new Region[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
    		 ResultSet resultSet = statement.executeQuery("SELECT name FROM region")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
