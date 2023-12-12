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
