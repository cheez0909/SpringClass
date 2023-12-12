/**
 * 1. 작성자 : Dani
 * 2. 작성일 : 12월 11일
 * 3. 내부 클래스
 *      - Inner 클래스 : 지역 내부 클래스
 */
package exam02;

public class Outer {

    Calculator method2(int c) {
        /**
         * 익명 내부 클래스
         */
        return new Calculator() {
            @Override
            public int add(int a, int b) {
                return a + b + c;
            }
        };
    }

    /**
     * 지역 내부 클래스
     */
    void method1(){

        class Inner {
            void innerMethod(){
                System.out.println("지역 내부 클래스");
            }
        }
        /**
         * static이 아니므로 객체 생성 후
         * 내부 메서드 불러오기
         */
        Inner in = new Inner();
        in.innerMethod();
    }

}
