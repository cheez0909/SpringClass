package org.choongang.restcontrollers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 회원 요청을 담는 커맨드 객체
 */
@Data
public class RequestJoin {
    /**
     * 유효성 검증 : Validation
     */
    @NotBlank(message = "아이디를 입력하세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String userPw;
    @NotBlank(message = "비밀번호를 확인하세요")
    private String confirmPw;
    @NotBlank(message = "회원명을 입력하세요")
    private String userNm;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDt;
}
