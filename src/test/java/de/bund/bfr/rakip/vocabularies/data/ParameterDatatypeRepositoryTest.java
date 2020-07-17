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

import de.bund.bfr.rakip.vocabularies.domain.ParameterDatatype;

public class ParameterDatatypeRepositoryTest {
	
	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		
		statement.execute("CREATE TABLE parameter_datatype ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(10) NOT NULL,"
				+ "comment VARCHAR(256),"
				+ "represented_as VARCHAR(8))");
		
		statement.execute("INSERT INTO parameter_datatype VALUES(0, 'name', 'comment', 'String')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}
	
	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {

		// Get mocked availability
		ParameterDatatypeRepository repository = new ParameterDatatypeRepository(connection);
		
		Optional<ParameterDatatype> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		ParameterDatatype procedure = optional.get();
		assertEquals(0, procedure.getId());
		assertEquals("name", procedure.getName());
		assertEquals("comment", procedure.getComment());
		assertEquals("String", procedure.getRepresentedAs());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		ParameterDatatypeRepository repository = new ParameterDatatypeRepository(connection);
		Optional<ParameterDatatype> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		ParameterDatatypeRepository repository = new ParameterDatatypeRepository(closedConnection);
		Optional<ParameterDatatype> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetAll() {
		// Get mocked availabilities
		ParameterDatatypeRepository repository = new ParameterDatatypeRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		ParameterDatatypeRepository repository = new ParameterDatatypeRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() {
		// Get mocked availabilities
		ParameterDatatypeRepository repository = new ParameterDatatypeRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		ParameterDatatypeRepository repository = new ParameterDatatypeRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
