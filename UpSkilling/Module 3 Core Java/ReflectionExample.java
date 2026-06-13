import java.lang.reflect.Method;

public class ReflectionExample {
    
    public void methodOne(String str) {
        System.out.println("Method One: " + str);
    }
    
    public int methodTwo(int a, int b) {
        return a + b;
    }
    
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("ReflectionExample");
            
            Object obj = clazz.getDeclaredConstructor().newInstance();
            
            Method[] methods = clazz.getDeclaredMethods();
            
            System.out.println("Methods in ReflectionExample:");
            for (Method method : methods) {
                System.out.println("Method: " + method.getName());
                System.out.print("Parameters: ");
                Class<?>[] params = method.getParameterTypes();
                for (Class<?> param : params) {
                    System.out.print(param.getName() + " ");
                }
                System.out.println();
            }
            
            Method method = clazz.getDeclaredMethod("methodOne", String.class);
            method.invoke(obj, "Hello from Reflection!");
            
            Method method2 = clazz.getDeclaredMethod("methodTwo", int.class, int.class);
            int result = (int) method2.invoke(obj, 5, 10);
            System.out.println("Method Two Result: " + result);
        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
