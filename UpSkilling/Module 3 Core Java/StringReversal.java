import java.util.Scanner;

public class StringReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        
        StringBuilder reversed = new StringBuilder(str);
        reversed.reverse();
        System.out.println("Reversed string (StringBuilder): " + reversed.toString());
        
        String reversedLoop = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedLoop += str.charAt(i);
        }
        System.out.println("Reversed string (Loop): " + reversedLoop);
        
        scanner.close();
    }
}
