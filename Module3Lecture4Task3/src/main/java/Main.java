import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final String FILENAME = "enrollments.ser";

    public static void main(String[] args) {
        // Create instances of Student, Course, and Enrollment
        Student student1 = new Student(1001, "John Smith", 20);
        Student student2 = new Student(1002, "Emma Johnson", 22);

        Course course1 = new Course("CS101", "Introduction to Programming", "Dr. Wilson");
        Course course2 = new Course("MATH201", "Advanced Mathematics", "Prof. Brown");

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Enrollment enrollment1 = new Enrollment(student1, course1, currentDate);
        Enrollment enrollment2 = new Enrollment(student2, course2, currentDate);

        // Serialize and save the Enrollment instances
        serializeEnrollments(new Enrollment[]{enrollment1, enrollment2});

        // Deserialize and print the Enrollment instances
        Enrollment[] loadedEnrollments = deserializeEnrollments();

        if (loadedEnrollments != null) {
            System.out.println("Deserialized Enrollment Information:");
            for (Enrollment enrollment : loadedEnrollments) {
                System.out.println(enrollment);
                System.out.println("---");
            }
        }
    }

    private static void serializeEnrollments(Enrollment[] enrollments) {
        try (FileOutputStream fileOut = new FileOutputStream(FILENAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(enrollments);
            System.out.println("Enrollments serialized and saved to " + FILENAME);

        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }

    private static Enrollment[] deserializeEnrollments() {
        try (FileInputStream fileIn = new FileInputStream(FILENAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            Enrollment[] enrollments = (Enrollment[]) objectIn.readObject();
            System.out.println("Enrollments deserialized from " + FILENAME);
            return enrollments;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
            return null;
        }
    }
}