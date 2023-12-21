package org.choongang.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder // 빌더를 추가하면 기본 생성자가 private으로 바뀐다
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    // 기본형 안됨 래퍼클래스
    // 널값이 기본값이라 유연하다
    // 기본키로 조회하는 메서드가 많이 있기 때문에
    // 기본키를 꼭 설정해줘야함
    // 이전에는 기본키를 설정하지 않았음!!
    @Id
    private Long userNo;
    private String userId;
    @JsonIgnore // 응답객체에서 제외
    private String userPw;
    private String userName;
    private String email;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm") // 날짜 형식화
    private LocalDateTime regDt;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm") // 날짜 형식화
    private LocalDateTime modDt;

}
