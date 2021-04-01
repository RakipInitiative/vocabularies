package de.bund.bfr.rakip.vocabularies.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import de.bund.bfr.rakip.vocabularies.domain.FishArea;

public class FishAreaRepository implements BasicRepository<FishArea> {

	private final Connection connection;

	public FishAreaRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<FishArea> getById(int id) {

		String query = "SELECT * FROM fish_area WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String name = resultSet.getString("name");
					String ssd = resultSet.getString("ssd");

					return Optional.of(new FishArea(id, name, ssd));
				}
			}
			return Optional.empty();
		} catch (SQLException err) {
			return Optional.empty();
		}
	}

	@Override
	public FishArea[] getAll() {

		ArrayList<FishArea> areaList = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM fish_area");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String ssd = resultSet.getString("ssd");

				areaList.add(new FishArea(id, name, ssd));
			}
		} catch (SQLException e) {
		}

		return areaList.toArray(new FishArea[0]);
	}
	
	@Override
	public String[] getAllNames() {

		ArrayList<String> names = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT name FROM fish_area");

			while (resultSet.next()) {
				names.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
		}

		return names.toArray(new String[0]);
	}
}
