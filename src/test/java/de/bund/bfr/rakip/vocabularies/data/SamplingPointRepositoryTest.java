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

import de.bund.bfr.rakip.vocabularies.domain.SamplingPoint;

public class SamplingPointRepositoryTest {

	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE sampling_point ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(255),"
				+ "sampnt CHAR(10),"
				+ "PRIMARY KEY(id))");
		
		statement.execute("INSERT INTO sampling_point VALUES(0, 'name', '12345')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}
	
	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {
		SamplingPointRepository repository = new SamplingPointRepository(connection);
		
		Optional<SamplingPoint> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		SamplingPoint samplingPoint = optional.get();
		assertEquals(0, samplingPoint.getId());
		assertEquals("name", samplingPoint.getName());
		assertEquals("12345", samplingPoint.getSampnt());
	}
	
	@Test
	public void testGetById_ExistingId_ShouldReturnEmptyOptional() {
		SamplingPointRepository repository = new SamplingPointRepository(connection);
		Optional<SamplingPoint> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		SamplingPointRepository repository = new SamplingPointRepository(closedConnection);
		Optional<SamplingPoint> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}

	@Test
	public void testGetAll() {
		SamplingPointRepository repository = new SamplingPointRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		SamplingPointRepository repository = new SamplingPointRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() throws Exception {
		// Get mocked hazards
		SamplingPointRepository repository = new SamplingPointRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		SamplingPointRepository repository = new SamplingPointRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
