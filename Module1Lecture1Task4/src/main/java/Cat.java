//The cat named Whiskers says: Meow!
//The cat named Whiskers says: Meow!
//The cat named Rex says: Meow!
//The cat named Whiskers says: Meow!

public class Cat {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public void meow() {
        System.out.println("The cat named " + name + " says: Meow!");
    }

    public static void main(String[] args) {
        // Create an instance of the Cat class with a name
        Cat catWhiskers = new Cat("Whiskers");
        Cat catRex = new Cat("Rex");

        // Call the meow method on the cat instance
        catWhiskers.meow();
        catWhiskers.meow();
        catRex.meow();
        catWhiskers.meow();
    }
}