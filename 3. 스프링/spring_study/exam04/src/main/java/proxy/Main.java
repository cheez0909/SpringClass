package proxy;

public class Main {
    public static void main(String[] args) {
        CalculatorImpl im = new CalculatorImpl();
        RecCalculator rc = new RecCalculator();
        long factorial = im.factorial(5L);
        // long factorial1 = rc.factorial(5L);
        System.out.println(factorial);
       //  System.out.println(factorial1);
    }
}
