package tests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

public class Ex1Test {

    @TempDir
    private File tmpFile;

    @Test
    void test1(){
        System.out.println(tmpFile.getAbsolutePath()); // 임시파일 경로
    }


    @Test
    @Timeout(3) // 성능테스트 할 때 몇 초안에 실행되는지
    void test2(){
        try{
            Thread.sleep(5000);
        } catch(InterruptedException e){

        }
    }

}
