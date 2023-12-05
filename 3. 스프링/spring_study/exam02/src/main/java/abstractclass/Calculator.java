package abstractclass;

public abstract class Calculator {

    int num = 20;

    protected static int num2 = 210;

    public abstract int add(int ... a);

    public void commonMethod(){
        System.out.println("하위 클래스의 공통기능을 정의함");
    }
}
