package entity;

import jakarta.persistence.*;

/**
 * Entity class representing a Currency in the database.
 * This corresponds to the currency table in the database.
 */
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "abbreviation", nullable = false, unique = true, length = 3)
    private String abbreviation;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "conversion_rate", nullable = false)
    private double conversionRate; // Conversion rate to USD

    // Default constructor required by JPA
    public Currency() {
    }

    // Constructor without id (for creating new currencies)
    public Currency(String abbreviation, String name, double conversionRate) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.conversionRate = conversionRate;
    }

    // Constructor with id (for currencies retrieved from database)
    public Currency(int id, String abbreviation, String name, double conversionRate) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.conversionRate = conversionRate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}

