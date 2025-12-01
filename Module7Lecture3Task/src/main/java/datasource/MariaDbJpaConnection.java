package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Singleton class for managing JPA EntityManager.
 * Provides a single EntityManager instance for the application.
 */
public class MariaDbJpaConnection {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    /**
     * Get the EntityManager instance.
     * Creates a new one if it doesn't exist.
     *
     * @return EntityManager instance, or null if connection fails
     */
    public static EntityManager getInstance() {
        if (em == null) {
            try {
                if (emf == null) {
                    emf = Persistence.createEntityManagerFactory("CurrencyConverterPU");
                }
                em = emf.createEntityManager();
                System.out.println("JPA EntityManager created successfully.");
            } catch (Exception e) {
                System.err.println("Failed to create EntityManager.");
                System.err.println("Please ensure that:");
                System.err.println("1. MariaDB server is running");
                System.err.println("2. The currency_converter database exists");
                System.err.println("3. The appuser account has been created with correct privileges");
                e.printStackTrace();
                return null;
            }
        }
        return em;
    }

    /**
     * Check if the EntityManager is available.
     *
     * @return true if EntityManager is available, false otherwise
     */
    public static boolean isAvailable() {
        try {
            EntityManager testEm = getInstance();
            return testEm != null && testEm.isOpen();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Close the EntityManager and EntityManagerFactory.
     */
    public static void close() {
        try {
            if (em != null && em.isOpen()) {
                em.close();
                System.out.println("EntityManager closed.");
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
                System.out.println("EntityManagerFactory closed.");
            }
        } catch (Exception e) {
            System.err.println("Error while closing EntityManager/Factory.");
            e.printStackTrace();
        }
    }
}

