/**
 * 1. 작성자 : Dani
 * 2. 작성일 : 12월 11일
 * 3. 지역 내부 클래스 불러오기
 */
package exam02;
public class OuterMain {
    public static void main(String[] args) {
        /**
         * 지역 내부 클래스 불러오기
         */
        Outer outer = new Outer();
        outer.method1();
        Calculator calculator = outer.method2(30);
        int add = calculator.add(10, 20);
        System.out.println(add);
        int add1 = calculator.add(40, 50);
        System.out.println(add1);
    }
}
