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

import de.bund.bfr.rakip.vocabularies.domain.Region;

public class RegionRepositoryTest {

	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE region ("
				+ "id INT not NULL,"
				+ "name VARCHAR(255),"
				+ "nuts CHAR(5) not NULL,"
				+ "PRIMARY KEY(id))");
		
		statement.execute("INSERT INTO region VALUES(0, 'name', 'nuts')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}
	
	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {
		RegionRepository repository = new RegionRepository(connection);
		
		Optional<Region> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		Region region = optional.get();
		assertEquals(0, region.getId());
		assertEquals("name", region.getName());
		assertEquals("nuts", region.getSsd());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		RegionRepository repository = new RegionRepository(connection);
		Optional<Region> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		RegionRepository repository = new RegionRepository(closedConnection);
		Optional<Region> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetAll() {
		RegionRepository repository = new RegionRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		RegionRepository repository = new RegionRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() throws Exception {
		// Get mocked hazards
		RegionRepository repository = new RegionRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		RegionRepository repository = new RegionRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
