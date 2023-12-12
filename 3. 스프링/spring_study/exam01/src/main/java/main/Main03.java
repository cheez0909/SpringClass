package main;

import java.util.Arrays;

public class Main03 {
    public static void main(String[] args) {
        System.out.println(add(10, 20, 30, 4,5,7));
    }

    public static int add(int ... nums){
//            int res = 0;
//        for (int num : nums) {
//            res += num;
//        }
//        System.out.println(res);
        return Arrays.stream(nums).sum();
    }
}
