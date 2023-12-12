package interfaceex;

import java.util.Arrays;
import java.util.Stack;

public interface Calculator {
    int num = 10; // public static final 정적 상수로 정의됨
    int add(int ... a); // public abstract

    default int minus(int ... a){
        int result = 0;
        for(int num : a){
            result -= num;
        }
        return result;
    }
}
