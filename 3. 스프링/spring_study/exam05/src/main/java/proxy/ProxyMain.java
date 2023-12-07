package proxy;

public class ProxyMain {
    public static void main(String[] args) {
        // 반복문
        ProcxyCalculator prim = new ProcxyCalculator(new Impl());
        System.out.println(prim.factorial(5L));

        // 재귀 함수
        ProcxyCalculator prrc = new ProcxyCalculator(new Reg());
        System.out.println(prrc.factorial(5L));


    }
}
