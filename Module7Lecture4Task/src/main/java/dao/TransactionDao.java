package dao;

import datasource.MariaDbJpaConnection;
import entity.Transaction;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for Transaction entities using JPA.
 * Handles all database operations related to currency exchange transactions.
 */
public class TransactionDao {

    /**
     * Persist a new transaction into the database using JPA.
     *
     * @param transaction The transaction to persist
     * @return true if successful, false otherwise
     */
    public boolean persist(Transaction transaction) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot persist transaction: EntityManager unavailable");
            return false;
        }

        try {
            em.getTransaction().begin();
            em.persist(transaction);
            em.getTransaction().commit();
            System.out.println("Transaction persisted successfully: " + transaction);
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error persisting transaction");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Find a transaction by its ID using JPA.
     *
     * @param id The transaction ID
     * @return Transaction object if found, null otherwise
     */
    public Transaction find(int id) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot find transaction: EntityManager unavailable");
            return null;
        }

        try {
            return em.find(Transaction.class, id);
        } catch (Exception e) {
            System.err.println("Error finding transaction with id: " + id);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieve all transactions from the database using JPA.
     *
     * @return List of all transactions, or empty list if error occurs
     */
    public List<Transaction> getAllTransactions() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        if (em == null) {
            System.err.println("Cannot retrieve transactions: EntityManager unavailable");
            return new ArrayList<>();
        }

        try {
            List<Transaction> transactions = em.createQuery("SELECT t FROM Transaction t", Transaction.class)
                    .getResultList();
            System.out.println("Retrieved " + transactions.size() + " transactions from database");
            return transactions;
        } catch (Exception e) {
            System.err.println("Error retrieving transactions from database");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

