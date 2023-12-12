package proxy;

public class Reg implements Calculator{
    @Override
    public long factorial(long num) {
        //long stime = System.nanoTime();
       // try {
            if (num < 1L) {
                return 1L;
            }
            return num * factorial(num - 1L);
        //}finally {
        //    long etime = System.nanoTime();
        //    System.out.println("걸린 시간 : " + (etime - stime));
        //}
        }
}
