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

import de.bund.bfr.rakip.vocabularies.domain.CollectionTool;

public class CollectionToolRepositoryTest {
	
	private static Connection connection;
	
	@BeforeClass
	public static void setUp() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE collection_tool ("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(50),"
				+ "PRIMARY KEY(id));");
		statement.execute("INSERT INTO collection_tool VALUES(0, 'name')");
	}
	
	@AfterClass
	public static void tearDown() throws SQLException {
		connection.close();
	}

	@Test
	public void testGetById_ExistingId_ShouldReturnPresentOptional() {
		
		// Get mocked collection tool
		CollectionToolRepository repository = new CollectionToolRepository(connection);
		
		Optional<CollectionTool> optional = repository.getById(0);
		assertTrue(optional.isPresent());
		
		CollectionTool collectionTool = optional.get();
		assertEquals(0, collectionTool.getId());
		assertEquals("name", collectionTool.getName());
	}
	
	@Test
	public void testGetById_MissingId_ShouldReturnEmptyOptional() {
		CollectionToolRepository repository = new CollectionToolRepository(connection);
		Optional<CollectionTool> optional = repository.getById(-1);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetById_ClosedConnection_ShouldReturnEmptyOptional() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		CollectionToolRepository repository = new CollectionToolRepository(closedConnection);
		Optional<CollectionTool> optional = repository.getById(0);
		assertFalse(optional.isPresent());
	}
	
	@Test
	public void testGetAll() {
		// Get mocked collection tools
		CollectionToolRepository repository = new CollectionToolRepository(connection);
		assertTrue(repository.getAll().length > 0);
	}

	@Test
	public void testGetAll_ClosedConnection_ShouldReturnEmptyArray() throws SQLException {
		Connection closedConnection = TestUtils.mockClosedConnection();
		CollectionToolRepository repository = new CollectionToolRepository(closedConnection);
		assertEquals(0, repository.getAll().length);
	}
	
	@Test
	public void testGetAllNames() {
		// Get mocked collection tools
		CollectionToolRepository repository = new CollectionToolRepository(connection);
		assertEquals("name", repository.getAllNames()[0]);
	}
	
	@Test
	public void testGetAllNames_ClosedConnection_ShouldReturnEmptyArray() throws Exception {
		Connection closedConnection = TestUtils.mockClosedConnection();
		CollectionToolRepository repository = new CollectionToolRepository(closedConnection);
		assertEquals(0, repository.getAllNames().length);
	}
}
