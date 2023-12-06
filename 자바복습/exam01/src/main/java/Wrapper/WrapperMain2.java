package Wrapper;

public class WrapperMain2 {
    public static void main(String[] args) {
        Integer i = new Integer(10);
        Integer i2 = new Integer(10);

        System.out.println(i==i2); // false
        System.out.println(i.equals(i2)); // true
        // 주소값이 다름
        System.out.println(System.identityHashCode(i)); 
        System.out.println(System.identityHashCode(i2));


        Integer i3 = Integer.valueOf(20);
        Integer i4 = Integer.valueOf(20);
        System.out.println(i3==i4); // true
        System.out.println(i3.equals(i4)); // true
        // 주소값이 일치함
        System.out.println(System.identityHashCode(i3));
        System.out.println(System.identityHashCode(i4));
    }
}
