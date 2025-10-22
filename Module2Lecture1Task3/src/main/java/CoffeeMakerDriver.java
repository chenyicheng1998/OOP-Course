public class CoffeeMakerDriver {
    public static void main(String[] args) {
        CoffeeMaker myCoffeeMaker = new CoffeeMaker();

        // Turn on the coffee machine
        myCoffeeMaker.pressOnOff();
        System.out.println("Coffee maker is " + (myCoffeeMaker.isOn() ? "on" : "off"));

        // Set the type and amount of coffee
        myCoffeeMaker.setCoffeeType("espresso");
        myCoffeeMaker.setCoffeeAmount(50);

        // Display the current Settings
        System.out.println("Coffee type is " + myCoffeeMaker.getCoffeeType());
        System.out.println("Coffee amount is " + myCoffeeMaker.getCoffeeAmount() + " ml");

        // Turn off the coffee machine
        myCoffeeMaker.pressOnOff();
        System.out.println("Coffee maker is " + (myCoffeeMaker.isOn() ? "on" : "off"));
    }
}