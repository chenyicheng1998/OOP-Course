package dao;

import datasource.MariaDbJpaConnection;
import entity.Currency;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for Currency entities using JPA.
 * Handles all database operations related to currencies.
 */
public class CurrencyDao {

    /**
     * Retrieve all currencies from the database using JPA.
     *
     * @return List of all currencies, or empty list if error occurs
     */
    public List<Currency> getAllCurrencies() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot retrieve currencies: EntityManager unavailable");
            return new ArrayList<>();
        }

        try {
            List<Currency> currencies = em.createQuery("SELECT c FROM Currency c", Currency.class)
                    .getResultList();
            System.out.println("Retrieved " + currencies.size() + " currencies using JPA");
            return currencies;
        } catch (Exception e) {
            System.err.println("Error retrieving currencies from database");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Find a currency by its ID using JPA.
     *
     * @param id The currency ID
     * @return Currency object if found, null otherwise
     */
    public Currency find(int id) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot find currency: EntityManager unavailable");
            return null;
        }

        try {
            return em.find(Currency.class, id);
        } catch (Exception e) {
            System.err.println("Error finding currency with id: " + id);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a currency by its abbreviation using JPA query.
     *
     * @param abbreviation The currency abbreviation (e.g., "USD", "EUR")
     * @return Currency object if found, null otherwise
     */
    public Currency getCurrencyByAbbreviation(String abbreviation) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot retrieve currency: EntityManager unavailable");
            return null;
        }

        try {
            List<Currency> results = em.createQuery(
                            "SELECT c FROM Currency c WHERE c.abbreviation = :abbr", Currency.class)
                    .setParameter("abbr", abbreviation)
                    .getResultList();

            if (!results.isEmpty()) {
                return results.get(0);
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error retrieving currency with abbreviation: " + abbreviation);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the conversion rate of a currency by its abbreviation.
     *
     * @param abbreviation The currency abbreviation (e.g., "USD", "EUR")
     * @return The conversion rate, or -1 if not found or error occurs
     */
    public double getConversionRate(String abbreviation) {
        Currency currency = getCurrencyByAbbreviation(abbreviation);
        if (currency != null) {
            return currency.getConversionRate();
        }
        return -1;
    }

    /**
     * Persist a new currency into the database using JPA.
     *
     * @param currency The currency to persist
     * @return true if successful, false otherwise
     */
    public boolean persist(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot persist currency: EntityManager unavailable");
            return false;
        }

        try {
            em.getTransaction().begin();
            em.persist(currency);
            em.getTransaction().commit();
            System.out.println("Currency persisted successfully: " + currency.getAbbreviation());
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error persisting currency: " + currency.getAbbreviation());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update an existing currency in the database using JPA.
     *
     * @param currency The currency to update
     * @return true if successful, false otherwise
     */
    public boolean update(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot update currency: EntityManager unavailable");
            return false;
        }

        try {
            em.getTransaction().begin();
            em.merge(currency);
            em.getTransaction().commit();
            System.out.println("Currency updated successfully: " + currency.getAbbreviation());
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error updating currency: " + currency.getAbbreviation());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a currency from the database using JPA.
     *
     * @param currency The currency to delete
     * @return true if successful, false otherwise
     */
    public boolean delete(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot delete currency: EntityManager unavailable");
            return false;
        }

        try {
            em.getTransaction().begin();
            // Make sure the entity is managed before removing
            Currency managedCurrency = em.merge(currency);
            em.remove(managedCurrency);
            em.getTransaction().commit();
            System.out.println("Currency deleted successfully: " + currency.getAbbreviation());
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error deleting currency: " + currency.getAbbreviation());
            e.printStackTrace();
            return false;
        }
    }
}

