package controller;

import dao.CurrencyDao;
import dao.TransactionDao;
import datasource.MariaDbJpaConnection;
import entity.Currency;
import entity.Transaction;

import java.util.List;

/**
 * Controller for the Currency Converter application.
 * Handles business logic and coordinates between the view and data access layer.
 */
public class CurrencyConverterController {
    private final CurrencyDao currencyDao;
    private final TransactionDao transactionDao;
    private List<Currency> currencies;

    public CurrencyConverterController() {
        currencyDao = new CurrencyDao();
        transactionDao = new TransactionDao();
        loadCurrenciesFromDatabase();
    }

    /**
     * Load currencies from the database using JPA.
     * If database is unavailable, returns an empty list and appropriate error will be shown.
     */
    private void loadCurrenciesFromDatabase() {
        currencies = currencyDao.getAllCurrencies();

        if (currencies.isEmpty()) {
            System.err.println("Warning: No currencies loaded from database");
        } else {
            System.out.println("Successfully loaded " + currencies.size() + " currencies from database");
        }
    }

    /**
     * Refresh the currency list from the database.
     * Useful after adding a new currency.
     */
    public void refreshCurrencies() {
        loadCurrenciesFromDatabase();
    }

    /**
     * Get all available currencies.
     *
     * @return List of currencies
     */
    public List<Currency> getCurrencies() {
        return currencies;
    }

    /**
     * Check if the database connection is available.
     *
     * @return true if database is available, false otherwise
     */
    public boolean isDatabaseAvailable() {
        return MariaDbJpaConnection.isAvailable();
    }

    /**
     * Convert an amount from one currency to another.
     * Fetches the latest conversion rates from the database and saves the transaction.
     *
     * @param amountText   The amount to convert as a string
     * @param fromCurrency The source currency
     * @param toCurrency   The target currency
     * @return The converted amount as a formatted string, or an error message starting with "ERROR:"
     */
    public String convert(String amountText, Currency fromCurrency, Currency toCurrency) {
        try {
            // Validation checks
            if (amountText == null || amountText.trim().isEmpty()) {
                return "ERROR: Please enter an amount to convert";
            }

            if (fromCurrency == null) {
                return "ERROR: Please select a source currency";
            }

            if (toCurrency == null) {
                return "ERROR: Please select a target currency";
            }

            // Check database availability
            if (!isDatabaseAvailable()) {
                return "ERROR: Database is not available. Please check your database connection.";
            }

            double amount = Double.parseDouble(amountText.trim());

            if (amount < 0) {
                return "ERROR: Amount cannot be negative";
            }

            // Fetch the latest conversion rates from database using JPA
            double fromRate = currencyDao.getConversionRate(fromCurrency.getAbbreviation());
            double toRate = currencyDao.getConversionRate(toCurrency.getAbbreviation());

            if (fromRate < 0 || toRate < 0) {
                return "ERROR: Unable to fetch conversion rates from database";
            }

            // Perform conversion
            double result = convertAmount(amount, fromRate, toRate);

            // Save transaction to database
            Transaction transaction = new Transaction(fromCurrency, toCurrency, amount, result);
            boolean saved = transactionDao.persist(transaction);

            if (!saved) {
                System.err.println("Warning: Transaction was not saved to database");
            }

            return String.format("%.2f", result);

        } catch (NumberFormatException e) {
            return "ERROR: Please enter a valid number for the amount";
        } catch (Exception e) {
            System.err.println("Unexpected error during conversion: " + e.getMessage());
            e.printStackTrace();
            return "ERROR: An unexpected error occurred during conversion";
        }
    }

    /**
     * Convert an amount from one currency to another using the given conversion rates.
     *
     * @param amount   The amount to convert
     * @param fromRate The conversion rate of the source currency to USD
     * @param toRate   The conversion rate of the target currency to USD
     * @return The converted amount
     */
    private double convertAmount(double amount, double fromRate, double toRate) {
        // Convert through USD as the base currency
        double amountInUSD = amount / fromRate;
        return amountInUSD * toRate;
    }

    /**
     * Add a new currency to the database.
     *
     * @param currency The currency to add
     * @return true if successful, false otherwise
     */
    public boolean addCurrency(Currency currency) {
        boolean success = currencyDao.persist(currency);
        if (success) {
            // Refresh the currency list after adding
            refreshCurrencies();
        }
        return success;
    }
}

