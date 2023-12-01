package ex04;

public class AnimalMain {
    public static void main(String[] args) {
        Animal a1 = new Animal("고양이", "야옹이", "야옹");
        System.out.printf("%s, %s, %s\n", a1.type(), a1.name(), a1.cry());
        System.out.println(a1);
        Animal a2 = new Animal("고양이", "야옹이", "냐옹");
        System.out.println(a2);
    }
}
