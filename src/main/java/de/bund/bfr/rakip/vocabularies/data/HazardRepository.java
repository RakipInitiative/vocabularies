package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.Hazard;

public class HazardRepository implements BasicRepository<Hazard> {

    private final Connection connection;

    public HazardRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Hazard> getById(int id) {

    	String query = "SELECT * FROM hazard WHERE id = ?";
    	try (PreparedStatement statement = connection.prepareStatement(query)) {
    	    statement.setInt(1, id);

    	    try (ResultSet resultSet = statement.executeQuery()) {
    	        if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String ssd = resultSet.getString("ssd");
                    return Optional.of(new Hazard(id, name, ssd));
                }
            }
    	    return Optional.empty();
        } catch (SQLException err) {
    	    return Optional.empty();
        }
    }

    @Override
    public Hazard[] getAll() {
    	
        ArrayList<Hazard> hazardList = new ArrayList<>();

    	try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM hazard")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String ssd = resultSet.getString("ssd");

                hazardList.add(new Hazard(id, name, ssd));
            }
    	} catch (SQLException err) {
    	}

        return hazardList.toArray(new Hazard[0]);
    }
    
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM hazard")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
