package de.bund.bfr.rakip.vocabularies.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.LaboratoryAccreditation;

public class LaboratoryAccreditationRepository implements BasicRepository<LaboratoryAccreditation> {

	private final Connection connection;

	public LaboratoryAccreditationRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<LaboratoryAccreditation> getById(int id) {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM laboratory_accreditation WHERE id = " + id);

			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("ssd");

				return Optional.of(new LaboratoryAccreditation(id, name, ssd));
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public LaboratoryAccreditation[] getAll() {
		ArrayList<LaboratoryAccreditation> accreditations = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM laboratory_accreditation");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("ssd");

				accreditations.add(new LaboratoryAccreditation(id, name, ssd));
			}
		} catch (SQLException err) {

		}

		return accreditations.toArray(new LaboratoryAccreditation[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery("SELECT name FROM laboratory_accreditation");
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
