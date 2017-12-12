package model;

import java.sql.*;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

/**
 * Klassen her skal ta seg av connection string til Databasen i Azure.
 * @author Luka
 *
 */

public class DatabaseAzure {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connect to database
			String hostName = "eu-cdbr-azure-north-e.cloudapp.net";
			String dbName = "";
			String user = "";
			String password = "";
			String url = ("jdbc:sqlserver://Database=db_td;Data Source=eu-cdbr-azure-north-e.cloudapp.net;User Id=b7d3c475078c79;Password=49c57a29;");
			Connection connection = null;

			try {
				connection = DriverManager.getConnection(url);
				String schema = connection.getSchema();
				System.out.println("Successful connection - Schema: " + schema);

				System.out.println("Query data example:");
				System.out.println("=========================================");

				// Create and execute a SELECT SQL statement.
				String selectSql = "SELECT * FROM db_td.trionakontaktliste;";

				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(selectSql)) {

					// Print results from select statement
					System.out.println("Top 20 categories:");
					while (resultSet.next())
					{
						System.out.println(resultSet.getString(1) + " "
								+ resultSet.getString(2));
					}
					connection.close();
				}                   
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

