public class OperatorPrecedence {
    public static void main(String[] args) {
        int result1 = 10 + 5 * 2;
        System.out.println("10 + 5 * 2 = " + result1 + " (Multiplication first: 5*2=10, then 10+10=20)");
        
        int result2 = (10 + 5) * 2;
        System.out.println("(10 + 5) * 2 = " + result2 + " (Addition first: 10+5=15, then 15*2=30)");
        
        int result3 = 20 / 5 - 2;
        System.out.println("20 / 5 - 2 = " + result3 + " (Division first: 20/5=4, then 4-2=2)");
        
        int result4 = 2 + 3 * 4 - 5;
        System.out.println("2 + 3 * 4 - 5 = " + result4 + " (Multiplication first: 3*4=12, then 2+12=14, then 14-5=9)");
    }
}
