import java.util.HashMap;
import java.util.Scanner;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        
        while (!choice.equals("4")) {
            System.out.println("\n1. Add student");
            System.out.println("2. Retrieve student by ID");
            System.out.println("3. Display all students");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.print("Enter student ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    studentMap.put(id, name);
                    System.out.println("Student added successfully!");
                    break;
                case "2":
                    System.out.print("Enter student ID to retrieve: ");
                    int searchId = Integer.parseInt(scanner.nextLine());
                    if (studentMap.containsKey(searchId)) {
                        System.out.println("Student name: " + studentMap.get(searchId));
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case "3":
                    System.out.println("\nAll students:");
                    studentMap.forEach((key, value) -> System.out.println("ID: " + key + ", Name: " + value));
                    break;
                case "4":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
        
        scanner.close();
    }
}
