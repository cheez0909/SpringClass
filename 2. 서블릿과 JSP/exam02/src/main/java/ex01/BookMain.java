package ex01;

public class BookMain {
    public static void main(String[] args) {

//        Book bk2 = new Book("스프링6", "최범균", "출판사");
//        System.out.println(bk2);

        Book bk1 = new Book("출판사");
        Book bk2 = new Book("출판사");
        bk2.setAuthor("작가");
        System.out.println(bk1==bk2); // 주소 비교, 동일성 비교
        System.out.println(bk1.equals(bk2)); // 동등성 비교
        System.out.println(bk1.hashCode() + " " + bk2.hashCode()); // 해시코드
    }
}
