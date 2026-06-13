import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for connections...");
            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");
            
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);
            
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Client: " + message);
                writer.println("Server received: " + message);
            }
            
            reader.close();
            writer.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
