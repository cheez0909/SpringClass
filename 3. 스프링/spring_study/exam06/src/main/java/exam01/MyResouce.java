package exam01;

// AutoCloseable를 구현하면 자원을 자동 해제해줌
public class MyResouce implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("자원해제....");
    }
}
