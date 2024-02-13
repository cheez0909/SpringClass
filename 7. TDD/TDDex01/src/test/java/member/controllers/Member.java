package member.controllers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String userId;
    private String userPw;
    private String confirmPw;
    private String userNm;
}
