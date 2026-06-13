public class TypeCastingExample {
    public static void main(String[] args) {
        // Cast double to int
        double doubleValue = 45.67;
        int intValue = (int) doubleValue;
        System.out.println("Original double: " + doubleValue);
        System.out.println("Casted to int: " + intValue);
        
        // Cast int to double
        int intValue2 = 100;
        double doubleValue2 = (double) intValue2;
        System.out.println("\nOriginal int: " + intValue2);
        System.out.println("Casted to double: " + doubleValue2);
    }
}
