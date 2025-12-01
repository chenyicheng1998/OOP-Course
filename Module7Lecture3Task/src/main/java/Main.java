import datasource.MariaDbJpaConnection;
import view.CurrencyConverterView;

/**
 * Main entry point for the Currency Converter application with JPA.
 */
public class Main {
    public static void main(String[] args) {
        // Add shutdown hook to close JPA EntityManager when application exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Application shutting down...");
            MariaDbJpaConnection.close();
        }));

        // Launch the JavaFX application
        CurrencyConverterView.launch(CurrencyConverterView.class, args);
    }
}

