interface Vehicle {
    /**
     * Prints a message indicating the vehicle is starting.
     */
    void start();

    /**
     * Prints a message indicating the vehicle is stopping.
     */
    void stop();

    /**
     * Returns a string with information about the vehicle.
     *
     * @return Vehicle information string.
     */
    String getInfo();

    /**
     * Calculates and returns the fuel efficiency.
     *
     * @return A string representing the fuel efficiency (e.g., "30 MPG" or "5 km/kWh").
     */
    String calculateFuelEfficiency();
}