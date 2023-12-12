package controllers.member;

import lombok.Data;

@Data
public class RequestJoin {
    private String id;
    private String name;
    private String pw;
    private String pwcheck;
    private String email;
    private boolean agree;
}
