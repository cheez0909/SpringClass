package interfaceex;

import java.util.Arrays;

public class SimpleCalculator implements Calculator{

    @Override
    public int add(int... a) {
       return Arrays.stream(a).sum();
    }
}
