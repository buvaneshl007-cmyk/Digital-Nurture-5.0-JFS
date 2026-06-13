import java.util.ArrayList;
import java.util.Scanner;
public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equals("3")) {
            System.out.println("\n1. Add name");
            System.out.println("2. Display all names");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter a name: ");
                    String name = scanner.nextLine();
                    names.add(name);
                    System.out.println("Name added successfully!");
                    break;
                case "2":
                    System.out.println("\nAll names:");
                    for (String n : names) {
                        System.out.println("- " + n);
                    }
                    break;
                case "3":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
        scanner.close();
    }
}
