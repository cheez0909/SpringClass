package man;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = new Animal[3];
        animals[0] = new Human();
        animals[1] = new Bird();
        animals[2] = new Dog();

        for(Animal ani : animals){
            if(ani instanceof Human){
                Human h1 = (Human) ani;
                h1.read();
            }
            ani.move();
        }
        // 부모 클래스의 Animal 객체의 move 메서드는 출력될 일이 없음
        // 추상클래스로 하는 것이 좋음

    }
}
