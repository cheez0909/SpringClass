package ex04;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Student {

    private String name;
    private String age;
    private String major;

}
