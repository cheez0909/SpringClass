package abstractclass;

import java.util.Arrays;

public class SimpleCalculator extends Calculator{

    @Override
    public int add(int... a) {
        return Arrays.stream(a).sum();
    }
}
