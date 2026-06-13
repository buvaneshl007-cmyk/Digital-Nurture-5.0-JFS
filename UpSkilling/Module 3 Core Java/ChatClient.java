import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server!");
            
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);
            
            Scanner scanner = new Scanner(System.in);
            String message;
            
            while (true) {
                System.out.print("Enter message: ");
                message = scanner.nextLine();
                writer.println(message);
                
                String response = reader.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
