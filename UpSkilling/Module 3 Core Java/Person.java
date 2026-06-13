import java.util.ArrayList;
import java.util.List;
public record Person(String name, int age) {}
class RecordExample {
    public static void main(String[] args) {
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 17);
        Person person3 = new Person("Charlie", 30);
        
        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);
        System.out.println("Person 3: " + person3);
        
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        
        System.out.println("\nPeople aged 18 or older:");
        people.stream()
                .filter(p -> p.age() >= 18)
                .forEach(System.out::println);
    }
}
