package Wrapper;

public class WrapperMain {
    public static void main(String[] args) {
        int num1 = 100;
        Integer num2 = new Integer(200);

        int num3 = num1 + num2; // num2.intValue() -> 컴파일러에서 자동 추가
        System.out.println(num3);

        Integer num4 = num3; // Integer.valueOf(num3) -> 컴파일러에서 자동 추가
        System.out.println(num4);

    }
}
