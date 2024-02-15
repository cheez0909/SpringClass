package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 에러를 공통된 제이슨 형식으로 담는 클래스
 * 에러 응답 데이터를 통일성 있게 만들기 위해서
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor // 기본생성자
public class JSONData<T> {
    // 성공했을때 , 에러가 없을때
    // 보통 성공을 많이 하니까 true를 기본값으로 설정하자!
    private boolean success = true;

    // 스프링에서 지원하는 응답 상태
    // 문자열임
    // 200이 가장 많이 나오므로, 성공시를 기본값으로 설정하자!
    private HttpStatus httpStatus = HttpStatus.OK;

    // 성공시 Body 데이터
    @NonNull
    private T data;

    // 실패시 메세지
    private String message;
}
