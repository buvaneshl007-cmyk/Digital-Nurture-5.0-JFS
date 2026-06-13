import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        String reversed = new StringBuilder(cleaned).reverse().toString();
        
        if (cleaned.equals(reversed)) {
            System.out.println("\"" + str + "\" is a palindrome.");
        } else {
            System.out.println("\"" + str + "\" is not a palindrome.");
        }
        
        scanner.close();
    }
}
