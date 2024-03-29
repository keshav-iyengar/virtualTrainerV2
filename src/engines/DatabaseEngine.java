package engines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseEngine {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/javadbtest";
	Connection conn = null;
	Statement stmt = null;

	public DatabaseEngine() {}

	public void openConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, "root", "root");
			System.out.println("Connected to DB successfully.");
			stmt = conn.createStatement();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void writeToDB(String query) {

		try {
			System.out.println("Inserting records...");
			stmt.executeUpdate(query);
			System.out.println("Records inserted. ");
		} catch(SQLException se) {
			System.out.println("Insert failed.");
			se.printStackTrace();
		}

	}

	public String readFromDB(String query) {
		try {
			System.out.println("Retrieving records...");
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Records retrieved. ");
			rs.next();
			return rs.getString(2);
		} catch(SQLException se) {
			se.printStackTrace();
			return "Read failed";
		}
	}

	public void closeConnection() {
		try {
			conn.close();
			System.out.println("Connection closed.");
		} catch(SQLException se) {
			System.out.println("Close connection failed.");
			se.printStackTrace();
		}
	}

}
