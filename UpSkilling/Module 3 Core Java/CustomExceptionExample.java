import java.util.Scanner;
public class CustomExceptionExample {
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older!");
        }
        System.out.println("Age is valid. You are " + age + " years old.");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            validateAge(age);
        } catch (InvalidAgeException e) {
            System.out.println("Invalid Age: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
