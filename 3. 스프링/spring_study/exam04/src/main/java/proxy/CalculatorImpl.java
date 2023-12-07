package proxy;

// 반복문
public class CalculatorImpl implements Calculator{

    @Override
    public long factorial(long num) {
        long total = 1L;
        for(long i=1L; 1<=num; i++){
            total *= num;
        }
        return total;
    }
}
