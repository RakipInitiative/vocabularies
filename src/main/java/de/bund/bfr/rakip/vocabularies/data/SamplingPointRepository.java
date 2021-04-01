package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.SamplingPoint;

import javax.xml.transform.Result;

public class SamplingPointRepository implements BasicRepository<SamplingPoint> {

	private final Connection connection;

	public SamplingPointRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<SamplingPoint> getById(int id) {

		String query = "SELECT * FROM sampling_point WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String sampnt = resultSet.getString("sampnt");
					return Optional.of(new SamplingPoint(id, name, sampnt));
				}
			}
			return Optional.empty();

		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public SamplingPoint[] getAll() {

		ArrayList<SamplingPoint> pointList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM sampling_point")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String sampnt = resultSet.getString("sampnt");

				pointList.add(new SamplingPoint(id, name, sampnt));
			}
		} catch (SQLException err) {
		}

		return pointList.toArray(new SamplingPoint[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
    		 ResultSet resultSet = statement.executeQuery("SELECT name FROM sampling_point")) {

    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
