import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter text to write to file: ");
            String text = scanner.nextLine();
            
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(text);
            fileWriter.close();
            
            System.out.println("Data written to output.txt successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
