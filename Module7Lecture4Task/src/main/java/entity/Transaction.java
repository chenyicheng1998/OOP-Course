package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Entity class representing a currency exchange Transaction.
 * Each transaction records a conversion from one currency to another.
 */
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "source_currency_id", nullable = false)
    private Currency sourceCurrency;

    @ManyToOne
    @JoinColumn(name = "target_currency_id", nullable = false)
    private Currency targetCurrency;

    @Column(nullable = false)
    private double sourceAmount;

    @Column(nullable = false)
    private double targetAmount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    // Default constructor required by JPA
    public Transaction() {
    }

    // Constructor for creating new transactions
    public Transaction(Currency sourceCurrency, Currency targetCurrency,
                       double sourceAmount, double targetAmount) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.transactionDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + transactionId +
                ", from=" + sourceCurrency.getAbbreviation() +
                ", to=" + targetCurrency.getAbbreviation() +
                ", amount=" + sourceAmount +
                ", result=" + targetAmount +
                ", date=" + transactionDate +
                '}';
    }
}

