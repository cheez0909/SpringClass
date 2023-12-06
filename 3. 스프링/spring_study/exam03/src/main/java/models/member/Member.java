package models.member;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class Member {
    private String userId;
    private String userPw;
    private String confirmPw;
    private String userName;
    private LocalDateTime regDt;
    private String regDtSt;
    private LocalDateTime regDt1;
}
