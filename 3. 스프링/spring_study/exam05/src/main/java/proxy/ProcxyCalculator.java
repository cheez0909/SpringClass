package proxy;

public class ProcxyCalculator implements Calculator {

    private Calculator calculator;

    public ProcxyCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public long factorial(long num) {
        long stime = System.nanoTime();

        try {
            long result = calculator.factorial(num);
            return result;
        }finally {
            long etime = System.nanoTime();
            System.out.println("걸린 시간 : " + (etime - stime));
        }
    }
}
