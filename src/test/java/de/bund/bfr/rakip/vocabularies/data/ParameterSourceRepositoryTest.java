package de.bund.bfr.rakip.vocabularies.data;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.bund.bfr.rakip.vocabularies.domain.ParameterSource;

public class ParameterSourceRepositoryTest {
	
	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE parameter_source ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(255),"
				+ "PRIMARY KEY(id))");
		
		statement.execute("INSERT INTO parameter_source VALUES(0, 'name')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}

	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {
		
		// Get mocked source
		ParameterSourceRepository repository = new ParameterSourceRepository(connection);
		
		Optional<ParameterSource> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		ParameterSource source = optional.get();
		assertEquals(0, source.getId());
		assertEquals("name", source.getName());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		ParameterSourceRepository repository = new ParameterSourceRepository(connection);
		Optional<ParameterSource> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		ParameterSourceRepository repository = new ParameterSourceRepository(closedConnection);
		Optional<ParameterSource> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetAll() {
		ParameterSourceRepository repository = new ParameterSourceRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		ParameterSourceRepository repository = new ParameterSourceRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() throws Exception {
		// Get mocked hazards
		ParameterSourceRepository repository = new ParameterSourceRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		ParameterSourceRepository repository = new ParameterSourceRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
