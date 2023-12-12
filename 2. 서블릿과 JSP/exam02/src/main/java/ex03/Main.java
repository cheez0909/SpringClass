package ex03;

public class Main {
    public static void main(String[] args) {
//        String str1="abc";
//        String str2="abc";
//        String str3 = "123";
//        System.out.println(System.identityHashCode(str1));
//        System.out.println(System.identityHashCode(str2));
//        // 값이 같다, 같은 주소값을 갖는다.
//        System.out.println(str1==str2); // "==" 는 주소 비교
//
//        // 값이 다르다, 다른 주소값을 갖는다.
//        System.out.println(System.identityHashCode(str3));
//        System.out.println("==============================");

        String str4 = new String("abc");
        String str5 = new String("abc");


        System.out.println("==============================");
        // 값이 갖지만 다른 주소값을 갖는다.
        System.out.println(System.identityHashCode(str4));
        System.out.println(System.identityHashCode(str5));
        System.out.println(str4==str5); // 주소값 비교
        System.out.println(str4.equals(str5)); // true, 논리적비교


        int[] str6 = {1,2,3,4,5,6};

        System.out.println("==============================");
        System.out.println(str6);
        System.out.println(System.identityHashCode(str6));
        System.out.println(str6.hashCode());
        System.out.println("==============================");
    }
}
