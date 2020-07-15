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

import de.bund.bfr.rakip.vocabularies.domain.LaboratoryAccreditation;

public class LaboratoryAccreditationTest {

	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE laboratory_accreditation ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(50),"
				+ "ssd VARCHAR(5),"
				+ "PRIMARY KEY(id))");
		
		statement.execute("INSERT INTO laboratory_accreditation VALUES(0, 'name', 'ssd')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}
	
	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {
		
		// Get mocked laboratory accreditation
		LaboratoryAccreditationRepository repository = new LaboratoryAccreditationRepository(connection);
		
		Optional<LaboratoryAccreditation> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		LaboratoryAccreditation accreditation = optional.get();
		assertEquals(0, accreditation.getId());
		assertEquals("name", accreditation.getName());
		assertEquals("ssd", accreditation.getSsd());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		LaboratoryAccreditationRepository repository = new LaboratoryAccreditationRepository(connection);
		Optional<LaboratoryAccreditation> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		LaboratoryAccreditationRepository repository = new LaboratoryAccreditationRepository(closedConnection);
		Optional<LaboratoryAccreditation> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetAll() {
		// Get mocked accreditations
		LaboratoryAccreditationRepository repository = new LaboratoryAccreditationRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}
	
	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		LaboratoryAccreditationRepository repository = new LaboratoryAccreditationRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() throws Exception {
		// Get mocked hazards
		LaboratoryAccreditationRepository repository = new LaboratoryAccreditationRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		LaboratoryAccreditationRepository repository = new LaboratoryAccreditationRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
