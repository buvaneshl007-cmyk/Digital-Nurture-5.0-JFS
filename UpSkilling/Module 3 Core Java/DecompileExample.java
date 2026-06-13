public class DecompileExample {
    private String name;
    private int age;
    
    public DecompileExample(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public boolean isAdult() {
        return age >= 18;
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
