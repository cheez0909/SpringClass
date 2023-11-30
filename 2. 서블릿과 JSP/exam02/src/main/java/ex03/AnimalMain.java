package ex03;

import java.util.HashSet;
import java.util.Set;

public class AnimalMain {
    public static void main(String[] args) {
        Animal animal = new Animal("야옹이", "포유류", "야옹");
        Animal animal1 = new Animal("야옹이", "포유류", "야옹");
        System.out.println(animal.hashCode());
        System.out.println(animal1.hashCode());
        System.out.println(animal.equals(animal1));

        Set<Animal> animals = new HashSet<>();
        animals.add(animal);
        animals.add(animal1);
        System.out.println(animals);
        System.out.println(animals.size());
    }
}
