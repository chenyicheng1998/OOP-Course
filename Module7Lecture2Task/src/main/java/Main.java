import datasource.MariaDbConnection;
import view.CurrencyConverterView;

/**
 * Main entry point for the Currency Converter application.
 */
public class Main {
    public static void main(String[] args) {
        // Add shutdown hook to close database connection when application exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Application shutting down...");
            MariaDbConnection.terminate();
        }));

        // Launch the JavaFX application
        CurrencyConverterView.launch(CurrencyConverterView.class, args);
    }
}