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

import de.bund.bfr.rakip.vocabularies.domain.AccreditationProcedure;

public class AccreditationProcedureRepositoryTest {
	
	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE accreditation_procedure ("
				+ "id INTEGER NOT NULL," 
				+ "mdstat VARCHAR(5) NOT NULL,"
				+ "name VARCHAR(50)," 
				+ "PRIMARY KEY(id))");
		
		statement.execute("INSERT INTO accreditation_procedure VALUES(0, 'ssd', 'name')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}
	
	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {

		// Get mocked availability
		AccreditationProcedureRepository repository = new AccreditationProcedureRepository(connection);
		
		Optional<AccreditationProcedure> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		AccreditationProcedure procedure = optional.get();
		assertEquals(0, procedure.getId());
		assertEquals("ssd", procedure.getMdstat());
		assertEquals("name", procedure.getName());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		AccreditationProcedureRepository repository = new AccreditationProcedureRepository(connection);
		Optional<AccreditationProcedure> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		AccreditationProcedureRepository repository = new AccreditationProcedureRepository(closedConnection);
		Optional<AccreditationProcedure> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetAll() {
		// Get mocked availabilities
		AccreditationProcedureRepository repository = new AccreditationProcedureRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		AccreditationProcedureRepository repository = new AccreditationProcedureRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() {
		// Get mocked availabilities
		AccreditationProcedureRepository repository = new AccreditationProcedureRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		AccreditationProcedureRepository repository = new AccreditationProcedureRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
