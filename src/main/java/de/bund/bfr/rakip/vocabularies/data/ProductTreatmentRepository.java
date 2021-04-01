package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.ProductTreatment;

public class ProductTreatmentRepository implements BasicRepository<ProductTreatment> {

	private final Connection connection;

	public ProductTreatmentRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<ProductTreatment> getById(int id) {

		String query = "SELECT * FROM prodTreat WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String ssd = resultSet.getString("ssd");
					String comment = resultSet.getString("comment");

					return Optional.of(new ProductTreatment(id, name, ssd, comment));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public ProductTreatment[] getAll() {

		ArrayList<ProductTreatment> treatmentList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery("SELECT * FROM prodTreat")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("ssd");
				String comment = resultSet.getString("comment");

				treatmentList.add(new ProductTreatment(id, name, ssd, comment));
			}
		} catch (SQLException err) {
		}

		return treatmentList.toArray(new ProductTreatment[0]);
	}
	
    @Override
    public String[] getAllNames() {
    	ArrayList<String> names = new ArrayList<>();
    	
    	try (Statement statement = connection.createStatement();
    		 ResultSet resultSet = statement.executeQuery("SELECT name FROM prodTreat")) {
    		
    		while (resultSet.next()) {
    			names.add(resultSet.getString("name"));
    		}
    	} catch (SQLException e) {}
    	
    	return names.toArray(new String[0]);
    }
}
