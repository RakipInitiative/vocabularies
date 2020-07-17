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

import de.bund.bfr.rakip.vocabularies.domain.TechnologyType;

public class TechnologyTypeRepositoryTest {

	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		
		statement.execute("CREATE TABLE technology_type ("
				+ "id INTEGER NOT NULL,"
				+ "ssd VARCHAR(5) NOT NULL,"
				+ "name VARCHAR(128) NOT NULL,"
				+ "comment VARCHAR(256),"
				+ "PRIMARY KEY(id))");
		statement.execute("INSERT INTO technology_type VALUES (0, 'ssd', 'name', 'comment')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}
	
	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {
		TechnologyTypeRepository repository = new TechnologyTypeRepository(connection);
		
		Optional<TechnologyType> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		TechnologyType unit = optional.get();
		assertEquals(0, unit.getId());
		assertEquals("name", unit.getName());
		assertEquals("ssd", unit.getSsd());
		assertEquals("comment", unit.getComment());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		TechnologyTypeRepository repository = new TechnologyTypeRepository(connection);
		Optional<TechnologyType> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		TechnologyTypeRepository repository = new TechnologyTypeRepository(closedConnection);
		Optional<TechnologyType> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}

	@Test
	public void testGetAll() {
		TechnologyTypeRepository repository = new TechnologyTypeRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		TechnologyTypeRepository repository = new TechnologyTypeRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() throws Exception {
		// Get mocked hazards
		TechnologyTypeRepository repository = new TechnologyTypeRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		TechnologyTypeRepository repository = new TechnologyTypeRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
