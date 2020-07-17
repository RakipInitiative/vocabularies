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

import de.bund.bfr.rakip.vocabularies.domain.BasicProcess;

public class BasicProcessRepositoryTest {

	private static Connection connection;

	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		
		statement.execute("CREATE TABLE model_class ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(64) NOT NULL,"
				+ "PRIMARY KEY(id))");
		statement.execute("INSERT INTO model_class (id, name) VALUES (0, 'model')");

		statement.execute("CREATE TABLE basic_process ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(128) NOT NULL," 
				+ "class_id INTEGER NOT NULL," 
				+ "PRIMARY KEY(id)," 
				+ "FOREIGN KEY(class_id) REFERENCES model_class(id))");
		
		statement.execute("INSERT INTO basic_process VALUES(0, 'submodel', 0)");
	}

	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}

	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {

		// Get mocked class
		BasicProcessRepository repository = new BasicProcessRepository(connection);

		Optional<BasicProcess> optional = repository.getById(0);
		assertTrue(optional.isPresent());

		BasicProcess subClass = optional.get();
		assertEquals(0, subClass.getId());
		assertEquals("submodel", subClass.getName());
		assertEquals(0, subClass.getClassCategory().getId());
	}

	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		BasicProcessRepository repository = new BasicProcessRepository(connection);
		Optional<BasicProcess> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}

	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		BasicProcessRepository repository = new BasicProcessRepository(closedConnection);
		Optional<BasicProcess> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}

	@Test
	public void testGetAll() {
		// Get mocked classes
		BasicProcessRepository repository = new BasicProcessRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}

	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		BasicProcessRepository repository = new BasicProcessRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}

	@Test
	public void testGetAllNames() throws Exception {
		// Get mocked hazards
		BasicProcessRepository repository = new BasicProcessRepository(connection);
		assertEquals("submodel", repository.getAllNames()[0]);
	}

	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		BasicProcessRepository repository = new BasicProcessRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
