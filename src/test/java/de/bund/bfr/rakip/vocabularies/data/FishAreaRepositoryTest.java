package de.bund.bfr.rakip.vocabularies.data;


import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.bund.bfr.rakip.vocabularies.domain.FishArea;

public class FishAreaRepositoryTest {

	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");

		Statement statement = connection.createStatement();

		statement.execute("CREATE TABLE fish_area (" + "id INTEGER NOT NULL," + "name VARCHAR(250) NOT NULL,"
				+ "ssd CHAR(10) NOT NULL);");

		statement.execute("INSERT INTO fish_area VALUES(0, 'name', 'ssd')");
	}

	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}

	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {

		// Get mocked fish area
		FishAreaRepository repository = new FishAreaRepository(connection);

		Optional<FishArea> optional = repository.getById(0);
		assertTrue(optional.isPresent());

		FishArea fishArea = optional.get();
		assertEquals(0, fishArea.getId());
		assertEquals("name", fishArea.getName());
		assertEquals("ssd", fishArea.getSsd());
	}

	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		FishAreaRepository repository = new FishAreaRepository(connection);
		Optional<FishArea> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		FishAreaRepository repository = new FishAreaRepository(closedConnection);
		Optional<FishArea> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}

	@Test
	public void testGetAll() {
		// Get mocked fish areas
		FishAreaRepository repository = new FishAreaRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		FishAreaRepository repository = new FishAreaRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() throws Exception {
		// Get mocked fish areas
		FishAreaRepository repository = new FishAreaRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		FishAreaRepository repository = new FishAreaRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
