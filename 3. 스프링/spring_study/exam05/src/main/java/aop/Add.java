package aop;

import java.util.Arrays;

public class Add implements CalculatorAdd{

    @Override
    public int add(int... num) {
        return Arrays.stream(num).sum();
    }
}
