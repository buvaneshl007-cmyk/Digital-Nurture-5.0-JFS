public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks: Woof! Woof!");
    }
    
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.makeSound();
        
        Dog dog = new Dog();
        dog.makeSound();
    }
}
