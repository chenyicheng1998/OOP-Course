package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Datasource class for managing database connections.
 * Uses singleton pattern to ensure only one connection is established.
 */
public class MariaDbConnection {

    private static Connection conn = null;

    /**
     * Get the database connection. Creates a new connection if one doesn't exist.
     *
     * @return Connection object to the currency_converter database
     */
    public static Connection getConnection() {
        if (conn == null) {
            // Connect if necessary
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/currency_converter?user=appuser&password=password");
                System.out.println("Database connection established successfully.");
            } catch (SQLException e) {
                System.err.println("Failed to connect to the database.");
                System.err.println("Please ensure that:");
                System.err.println("1. MariaDB server is running");
                System.err.println("2. The currency_converter database exists");
                System.err.println("3. The appuser account has been created with correct privileges");
                e.printStackTrace();
                return null;
            }
        }
        return conn;
    }

    /**
     * Test if the database connection is available and working.
     *
     * @return true if connection is available, false otherwise
     */
    public static boolean isConnectionAvailable() {
        try {
            Connection testConn = getConnection();
            return testConn != null && !testConn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Close the database connection.
     */
    public static void terminate() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error while closing the database connection.");
            e.printStackTrace();
        }
    }
}

