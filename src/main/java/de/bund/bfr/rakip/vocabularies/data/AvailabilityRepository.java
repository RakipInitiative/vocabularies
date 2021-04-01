package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Availability;

public class AvailabilityRepository implements BasicRepository<Availability> {

    private final Connection connection;

    public AvailabilityRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Availability> getById(int id) {

        String query = "SELECT * FROM availability WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setInt(1, id);

        	try (ResultSet resultSet = statement.executeQuery()) {
        		if (resultSet.next()) {
				    String name = resultSet.getString("name");
				    String comment = resultSet.getString("comment");
				    return Optional.of(new Availability(id, name, comment));
				}
			}
        	return Optional.empty();
		} catch (SQLException err) {
        	return Optional.empty();
		}
    }

    @Override
    public Availability[] getAll() {
    	
        ArrayList<Availability> availabilityList = new ArrayList<>();
    	
		try {
			Statement statement = connection.createStatement();
			
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM availability");

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String comment = resultSet.getString("comment");

	            availabilityList.add(new Availability(id, name, comment));
	        }
		} catch (SQLException e) {
		}

        return availabilityList.toArray(new Availability[0]);
    }
    
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    				
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM availability");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {
    	}
    	
    	return names.toArray(new String[0]);
    }
}
