package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.SamplingProgram;

public class SamplingProgramRepository implements BasicRepository<SamplingProgram> {

	private final Connection connection;

	public SamplingProgramRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<SamplingProgram> getById(int id) {

		String query = "SELECT * FROM sampling_program WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String progType = resultSet.getString("progType");
					return Optional.of(new SamplingProgram(id, name, progType));
				}
			}

			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public SamplingProgram[] getAll() {

		ArrayList<SamplingProgram> programs = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM sampling_program")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String progType = resultSet.getString("progType");

				programs.add(new SamplingProgram(id, name, progType));
			}
		} catch (SQLException err) {
		}

		return programs.toArray(new SamplingProgram[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
    		 ResultSet resultSet = statement.executeQuery("SELECT name FROM sampling_program")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
