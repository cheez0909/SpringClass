package ex02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private int id;
    private String name;
    private String subject;

    public void studying(){
        System.out.println(name+"이 " + subject +"공부를 합니다.");
    }
}
