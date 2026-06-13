import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClientExample {
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/github"))
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:");
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("HTTP request error: " + e.getMessage());
        }
    }
}
