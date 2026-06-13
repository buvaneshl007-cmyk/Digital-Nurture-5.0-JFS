import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpressions {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Mango");
        fruits.add("Orange");
        
        System.out.println("Original list:");
        fruits.forEach(System.out::println);
        
        Collections.sort(fruits, (a, b) -> a.compareTo(b));
        
        System.out.println("\nSorted list:");
        fruits.forEach(System.out::println);
    }
}
