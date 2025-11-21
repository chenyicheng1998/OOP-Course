package controller;

import model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConverterController {
    private List<Currency> currencies;

    public CurrencyConverterController() {
        initializeCurrencies();
    }

    private void initializeCurrencies() {
        currencies = new ArrayList<>();
        currencies.add(new Currency("USD", "US Dollar", 1.0));
        currencies.add(new Currency("EUR", "Euro", 0.85));
        currencies.add(new Currency("GBP", "British Pound", 0.73));
        currencies.add(new Currency("JPY", "Japanese Yen", 110.25));
        currencies.add(new Currency("CAD", "Canadian Dollar", 1.25));
        currencies.add(new Currency("AUD", "Australian Dollar", 1.32));
        currencies.add(new Currency("CNY", "Chinese Yuan", 6.45));
        currencies.add(new Currency("INR", "Indian Rupee", 74.50));
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public String convert(String amountText, Currency fromCurrency, Currency toCurrency) {
        try {
            if (amountText == null || amountText.trim().isEmpty()) {
                return "ERROR: Please enter an amount to convert";
            }

            if (fromCurrency == null) {
                return "ERROR: Please select a source currency";
            }

            if (toCurrency == null) {
                return "ERROR: Please select a target currency";
            }

            double amount = Double.parseDouble(amountText.trim());

            if (amount < 0) {
                return "ERROR: Amount cannot be negative";
            }

            double result = convertAmount(amount, fromCurrency, toCurrency);
            return String.format("%.2f", result);

        } catch (NumberFormatException e) {
            return "ERROR: Please enter a valid number for the amount";
        }
    }

    private double convertAmount(double amount, Currency fromCurrency, Currency toCurrency) {
        // Convert through USD as the base currency
        double amountInUSD = amount / fromCurrency.getConversionRate();
        return amountInUSD * toCurrency.getConversionRate();
    }
}