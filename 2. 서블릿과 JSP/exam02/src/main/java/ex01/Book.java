package ex01;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE) // 기본 생성자, 생성자의 범위 지정
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book {
    private String name;
    @With  private String author;
    @NonNull  private String publisher;
}
