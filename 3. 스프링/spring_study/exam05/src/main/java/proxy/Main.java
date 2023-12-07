package proxy;

public class Main {
    public static void main(String[] args) {
        Impl im = new Impl();
        Reg rg = new Reg();
        //long stime = System.nanoTime();
        System.out.println(im.factorial(5L));
        //long etime = System.nanoTime();
        //System.out.println("for 문 : "+(etime-stime));

        //long stime1 = System.nanoTime();
        System.out.println(rg.factorial(5L));
        //long etime1 = System.nanoTime();
        //System.out.println("재귀함수 : "+(etime1-stime1));
    }

}
