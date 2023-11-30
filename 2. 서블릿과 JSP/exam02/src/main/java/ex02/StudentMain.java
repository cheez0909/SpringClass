package ex02;

public class StudentMain {
    public static void main(String[] args) {

        // 객체 생성
        // Student student1 = new Student(20231130, "홍길동", "수학");
        // student1은 인스턴스
        // student1.studying();

        
        // add(10, 20); // 호출되었을때 메모리 공간을 할당함

        Student s1 = new Student(20231130, "홍길동", "수학");
        System.out.println("S1 : " + System.identityHashCode(s1)); // 주소
        Student s2 = s1;
        s2.setId(20231201);
        s2.setName("장장장");
        System.out.println("S2 : " +System.identityHashCode(s2));
    }

    // 스택에 메모리 공간을 할당한 후
    // 연산이 끝나고 사라짐
    // 스택메모리 : 임시적으로 사용됨
    // 함수는 스택의 구조로 짜여져 있다.
    // 매개변수는 아직 변수가 아님
    // 메모리에 할당을 받아야 변수가 된다.
    // 변수 : 공간
    // 연산을 하기위해 메모리가 필요함

    public static int add(int num1, int num2){
        int result = num1 + num2;
        return result;
    }
}
