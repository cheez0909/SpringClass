package interfaceex;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Calculator cal = new SimpleCalculator();
        cal.add(1,5,7);
        System.out.println(cal.num);

        cal.minus(1,5,7);
        Calculator cal2 = new Calculator() {
            @Override
            public int add(int... a) {
                return Arrays.stream(a).sum();
            }
        };


    }
}
