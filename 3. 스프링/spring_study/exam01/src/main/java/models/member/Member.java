package models.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private String userId;
    private String userPw;
    private String confirmPw;
    private String userName;
    private LocalDateTime regDt;
}
