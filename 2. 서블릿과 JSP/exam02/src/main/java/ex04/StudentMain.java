package ex04;

public class StudentMain {
    public static void main(String[] args) {
        Student s2 = Student.builder().name("홍길동").age("23").major("컴공").build();
        System.out.println(s2);
    }
}
