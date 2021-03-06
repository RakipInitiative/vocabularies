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

import de.bund.bfr.rakip.vocabularies.domain.Country;

public class CountryRepositoryTest {
	
	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE country ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(255) NOT NULL,"
				+ "iso CHAR(2) NOT NULL,"
				+ "PRIMARY KEY(id));");
		
		statement.execute("INSERT INTO country VALUES(0, 'name', 'ES')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}

	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional()  {
		
		// Get mocked country
		CountryRepository repository = new CountryRepository(connection);
		
		Optional<Country> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		Country country = optional.get();
		assertEquals(0, country.getId());
		assertEquals("name", country.getName());
		assertEquals("ES", country.getIso());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		CountryRepository repository = new CountryRepository(connection);
		Optional<Country> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		CountryRepository repository = new CountryRepository(closedConnection);
		Optional<Country> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetAll()  {
		// Get mocked countries
		CountryRepository repository = new CountryRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		CountryRepository repository = new CountryRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() {
		// Get mocked countries
		CountryRepository repository = new CountryRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		CountryRepository repository = new CountryRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
