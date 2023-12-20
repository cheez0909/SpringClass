package models.member;

import common.CommonException;
import org.springframework.http.HttpStatus;

/**
 * 공통 에러 클래스를 상속받음
 */
public class MemberNotFoundException extends CommonException {
    public MemberNotFoundException(){
        // 부모 클래스의 생성자
        super("등록되지 않은 회원입니다.", HttpStatus.NOT_FOUND); // 404에러
    }
}
