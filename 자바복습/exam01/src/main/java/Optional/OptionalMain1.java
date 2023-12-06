package Optional;

import java.util.Optional;

public class OptionalMain1 {
    public static void main(String[] args) {
        // 널값을 처리하기 위함

        String str = null;
       // Optional<String> opt = Optional.of(str); // 값이 null이면 예외 발생(NPE)

        Optional<String> opt1 = Optional.ofNullable(str); // null을 허용함
        System.out.println(opt1); // 빈 객체로 표시됨
        // String str2 = opt1.get(); // 값이 없으면 예외 발생(NoSuchElementException)

        String str3 = opt1.orElse("널입니다"); // 널일경우 기본값
        System.out.println(str3);

//        String str4 = opt1.orElse(null);
//        if(str4 == null){
//            throw new RuntimeException("값이 없음 예외 발생");
//        }

        // 오류 던지기
//        String str5 = opt1.orElseThrow();

        String str6 = opt1.orElseThrow(()->new RuntimeException("값이 없음, 예외 발생"));

    }
}
