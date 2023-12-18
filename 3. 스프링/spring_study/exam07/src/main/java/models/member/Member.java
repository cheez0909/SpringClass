package models.member;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class Member {
    private long userNo; // 기본형보다 wrapper 클래스를 쓰는 것이 좋음
    private String userId;
    private String userPw;
    private String userName;
    private String email;
    private LocalDateTime regDt;
    private LocalDateTime modDt;

}
