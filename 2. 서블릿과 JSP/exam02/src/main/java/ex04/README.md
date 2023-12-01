
### 1. 롬복 라이브러리
___
#### 📑 @Builder (📂ex04 / Book, Student)
_클래스에 작성하면 모든 필드에 대한 빌더를 만들어준다. 원하는 필드에 대해서만 빌더를 작성하고 싶은 경우 생성자를 작성하고 그 위에 @Builder를 붙여주면 된다._

#### 💾 Book 클래스
```
import lombok.*;

// @Builder 메서드 체이닝
@Getter
@Setter
public class Book {
    private String title;

    private String author;

    private String publisher;

    private Book() {}

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
    // @Builder
    public static Builder builder(){
        return new Builder();
    }

    protected static class Builder{
        private Book instance = new Book();

        public Builder title(String title){
            instance.title = title;
            return this;
        }
        public Builder author(String author){
            instance.author = author;
            return this;
        }
        public Builder publisher(String publisher){
            instance.publisher = publisher;
            return this;
        }
        public Book build(){
            return instance;
        }
    }
}
```
#### 💾 Book Main
```
package ex04;

public class BookMain {
    public static void main(String[] args) {
        Book b1 = Book.builder()
                .title("제목")
                .author("저자")
                .publisher("출판사")
                .build();

        System.out.println(b1);

        Book b2 = Book.builder()
                .publisher("출판사")
                .title("제목")
                .build();

        b2.setTitle("타이틀 변경");
        b2.setAuthor("작가 추가");

        System.out.println(b2);


    }
}
```
#### 🔵 실행결과
```
Book{title='제목', author='저자', publisher='출판사'}
Book{title='타이틀 변경', author='작가 추가', publisher='출판사'}
```
<br>
<br>

#### 💾 Student 클래스
```
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

```


#### 💾 StudentMain
```
package ex04;

public class StudentMain {
    public static void main(String[] args) {
        Student s2 = Student.builder().name("홍길동").age("23").major("컴공").build();
        System.out.println(s2);
    }
}

```

#### 🔵 실행결과
```
Student(name=홍길동, age=23, major=컴공)
```

<br>
<br>

### 2. record 타입(📂ex04 / Animal, AnimalMain)
___
```
public record Animal(    String type,
                         String name,
                         String cry) {
}
```

✅ 불변(immutable) 데이터 객체를 쉽게 생성할 수 있도록 하는 새로운 유형의 클래스<br>

#### ❓ 기존 불변객체 생성 시 문제점
- 모든 필드에 `final` 사용
- 모든 필드값을 포함한 생성자
- 모든 필드에 대한 getter 메서드
- toString 재정의
- `hashcode`, `equals` 재정의
> 많은 코드작성이 필요함

<br>

#### 🔑 레코드를 이용한다면..?
- 생성자를 작성하지 않아도 되고, `toString`, `equals`, `hashCode`에 대한 구현을 자동으로 제공
<br>
<br>

```
public record Animal(    String type,
                         String name,
                         String cry) {
    public Animal{
    if(cry.equals("야옹")){
        System.out.println("야옹이안녕");
    }
}
}
```
> 조건을 걸 수 있음

