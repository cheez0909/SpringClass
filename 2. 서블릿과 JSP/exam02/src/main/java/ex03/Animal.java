package ex03;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Animal {
    private String name;
    private String type;
    private String cry;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) && Objects.equals(type, animal.type) && Objects.equals(cry, animal.cry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, cry);
    }
}
