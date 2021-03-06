package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.HazardType;

public class HazardTypeRepository implements BasicRepository<HazardType> {

	private final Connection connection;

	public HazardTypeRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<HazardType> getById(int id) {

		String query = "SELECT * FROM hazard_type WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					return Optional.of(new HazardType(id, name));
				}
				return Optional.empty();
			}
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public HazardType[] getAll() {

		ArrayList<HazardType> typeList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM hazard_type")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				typeList.add(new HazardType(id, name));
			}
		} catch (SQLException err) {
		}

		return typeList.toArray(new HazardType[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT name FROM hazard_type")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
