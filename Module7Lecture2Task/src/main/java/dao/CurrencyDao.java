package dao;

import datasource.MariaDbConnection;
import entity.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for Currency entities.
 * Handles all database operations related to currencies.
 */
public class CurrencyDao {

    /**
     * Retrieve all currencies from the database.
     *
     * @return List of all currencies, or empty list if error occurs
     */
    public List<Currency> getAllCurrencies() {
        Connection conn = MariaDbConnection.getConnection();
        if (conn == null) {
            System.err.println("Cannot retrieve currencies: database connection unavailable");
            return new ArrayList<>();
        }

        String sql = "SELECT id, abbreviation, name, conversion_rate FROM currency";
        List<Currency> currencies = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                String abbreviation = rs.getString(2);
                String name = rs.getString(3);
                double conversionRate = rs.getDouble(4);

                Currency currency = new Currency(id, abbreviation, name, conversionRate);
                currencies.add(currency);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving currencies from database");
            e.printStackTrace();
        }

        return currencies;
    }

    /**
     * Get a currency by its abbreviation.
     *
     * @param abbreviation The currency abbreviation (e.g., "USD", "EUR")
     * @return Currency object if found, null otherwise
     */
    public Currency getCurrencyByAbbreviation(String abbreviation) {
        Connection conn = MariaDbConnection.getConnection();
        if (conn == null) {
            System.err.println("Cannot retrieve currency: database connection unavailable");
            return null;
        }

        String sql = "SELECT id, abbreviation, name, conversion_rate FROM currency WHERE abbreviation = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, abbreviation);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String abbr = rs.getString(2);
                String name = rs.getString(3);
                double conversionRate = rs.getDouble(4);

                return new Currency(id, abbr, name, conversionRate);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving currency with abbreviation: " + abbreviation);
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get the conversion rate of a currency by its abbreviation.
     *
     * @param abbreviation The currency abbreviation (e.g., "USD", "EUR")
     * @return The conversion rate, or -1 if not found or error occurs
     */
    public double getConversionRate(String abbreviation) {
        Connection conn = MariaDbConnection.getConnection();
        if (conn == null) {
            System.err.println("Cannot retrieve conversion rate: database connection unavailable");
            return -1;
        }

        String sql = "SELECT conversion_rate FROM currency WHERE abbreviation = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, abbreviation);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving conversion rate for: " + abbreviation);
            e.printStackTrace();
        }

        return -1; // Return -1 to indicate error or not found
    }

    /**
     * Insert a new currency into the database.
     *
     * @param currency The currency to persist
     * @return true if successful, false otherwise
     */
    public boolean persist(Currency currency) {
        Connection conn = MariaDbConnection.getConnection();
        if (conn == null) {
            System.err.println("Cannot persist currency: database connection unavailable");
            return false;
        }

        String sql = "INSERT INTO currency (abbreviation, name, conversion_rate) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, currency.getAbbreviation());
            ps.setString(2, currency.getName());
            ps.setDouble(3, currency.getConversionRate());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error persisting currency: " + currency.getAbbreviation());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update an existing currency in the database.
     *
     * @param currency The currency to update
     * @return true if successful, false otherwise
     */
    public boolean update(Currency currency) {
        Connection conn = MariaDbConnection.getConnection();
        if (conn == null) {
            System.err.println("Cannot update currency: database connection unavailable");
            return false;
        }

        String sql = "UPDATE currency SET name = ?, conversion_rate = ? WHERE abbreviation = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, currency.getName());
            ps.setDouble(2, currency.getConversionRate());
            ps.setString(3, currency.getAbbreviation());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating currency: " + currency.getAbbreviation());
            e.printStackTrace();
            return false;
        }
    }
}

