package de.bund.bfr.rakip.vocabularies.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.TechnologyType;

public class TechnologyTypeRepository implements BasicRepository<TechnologyType> {

    private final Connection connection;

    public TechnologyTypeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<TechnologyType> getById(int id) {

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM technology_type WHERE id = " + id);
        	
			if (resultSet.next()) {
				String ssd = resultSet.getString("ssd");
			    String name = resultSet.getString("name");
			    String comment = resultSet.getString("comment");

			    return Optional.of(new TechnologyType(id, ssd, name, comment));
			}
			
			return Optional.empty();
		} catch (SQLException e) {
			return Optional.empty();
		}
    }

    @Override
    public TechnologyType[] getAll() {
    	
        ArrayList<TechnologyType> typeList = new ArrayList<>();
    	
		try {
			Statement statement = connection.createStatement();
			
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM technology_type");

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String ssd = resultSet.getString("ssd");
	            String name = resultSet.getString("name");
	            String comment = resultSet.getString("comment");

	            typeList.add(new TechnologyType(id, ssd, name, comment));
	        }
		} catch (SQLException e) {
		}

        return typeList.toArray(new TechnologyType[0]);
    }
    
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    				
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM technology_type");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {
    	}
    	
    	return names.toArray(new String[0]);
    }
}
