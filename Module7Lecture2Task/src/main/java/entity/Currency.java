package entity;

/**
 * Entity class representing a Currency in the database.
 * This corresponds to the currency table in the database.
 */
public class Currency {
    private int id;
    private String abbreviation;
    private String name;
    private double conversionRate; // Conversion rate to USD

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

