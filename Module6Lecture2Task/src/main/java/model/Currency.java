package model;

public class Currency {
    private String abbreviation;
    private String name;
    private double conversionRate; // Conversion rate to USD

    public Currency(String abbreviation, String name, double conversionRate) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.conversionRate = conversionRate;
    }

    // Getters
    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}