package abstractclass;

import java.util.LinkedHashMap;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        SimpleCalculator sim = new SimpleCalculator();
        System.out.println(sim.add(1, 2, 3, 4, 5, 6, 7));
        System.out.println(sim.num); // 추상클래스도 생성이 되어있는 것
//        System.out.println(sim.num2); 인스턴스 자원만 상속이 가능하다.

        // 추상클래스는 하위 클래스의 공통 기능

        Calculator cal = new SimpleCalculator();
        System.out.println(sim instanceof Calculator);


    }
}
