package proxy;

public class Impl implements Calculator {
    @Override
    public long factorial(long num) {
        //long stime = System.nanoTime();
        //try {
            long total = 1L;
            for (long i = 1L; i <= num; i++) {
                total *= i;
            }
            return total;
        //} finally {
        //    long etime = System.nanoTime();
        //    System.out.println("걸린 시간 : " + (etime -stime));
        //}
    }
}
