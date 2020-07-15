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

import de.bund.bfr.rakip.vocabularies.domain.Population;

public class PopulationRepositoryTest {
	
	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE population ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(255),"
				+ "foodon CHAR(10),"
				+ "PRIMARY KEY(id))");
		
		statement.execute("INSERT INTO population VALUES(0, 'name', 'foodon')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}
	
	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {
		
		// Get mocked population
		PopulationRepository repository = new PopulationRepository(connection);
		
		Optional<Population> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		Population population = optional.get();
		assertEquals(0, population.getId());
		assertEquals("name", population.getName());
		assertEquals("foodon", population.getFoodon());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		PopulationRepository repository = new PopulationRepository(connection);
		Optional<Population> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		PopulationRepository repository = new PopulationRepository(closedConnection);
		Optional<Population> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetAll() {
		PopulationRepository repository = new PopulationRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		PopulationRepository repository = new PopulationRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() throws Exception {
		// Get mocked hazards
		PopulationRepository repository = new PopulationRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		PopulationRepository repository = new PopulationRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
