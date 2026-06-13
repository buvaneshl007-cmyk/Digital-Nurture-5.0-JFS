public class PatternMatching {
    
    public static void describeObject(Object obj) {
        String result = switch(obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case Double d -> "Double: " + d;
            case Boolean b -> "Boolean: " + b;
            default -> "Unknown type";
        };
        System.out.println(result);
    }
    
    public static void main(String[] args) {
        describeObject(42);
        describeObject("Hello");
        describeObject(3.14);
        describeObject(true);
        describeObject(new ArrayList<>());
    }
}

import java.util.ArrayList;
