package de.bund.bfr.rakip.vocabularies.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Util {

	private Util() {
	}

	public static void loadInitialData(Connection connection) throws IOException {

		String tableFile = Util.class.getClassLoader().getResource("data/tables.sql").getFile();
		try {
			System.out.println("Creating tables");
			String script = FileUtils.readFileToString(new File(tableFile), "UTF-8");

			Statement statement = connection.createStatement();
			statement.execute(script);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		// Insert data
		List<String> filenames = Arrays.asList("availability.sql", "collection_tool.sql", "country.sql",
				"fish_area.sql", "format.sql", "hazard_type.sql", "hazard.sql", "ind_sum.sql",
				"laboratory_accreditation.sql", "language_written_in.sql", "language.sql", "model_class.sql",
				"model_equation_class.sql", "packaging.sql", "parameter_distribution.sql", "parameter_source.sql",
				"parameter_subject.sql", "population.sql", "prodmeth.sql", "prodTreat.sql", "product_matrix.sql",
				"publication_status.sql", "region.sql", "rights.sql", "sampling_method.sql", "sampling_point.sql",
				"sampling_program.sql", "software.sql", "sources.sql", "status.sql", "unit.sql");

		for (String filename : filenames) {

			System.out.println("Loading " + filename);

			String filePath = Util.class.getClassLoader().getResource("data/initialdata/" + filename).getFile();
			File file = new File(filePath);

			Statement statement;
			try {
				statement = connection.createStatement();
				for (String line : FileUtils.readLines(file, "UTF-8")) {
					statement.execute(line);
				}
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
