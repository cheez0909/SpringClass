package controllers.member;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RequestJoin {
    /**
     * Join.jsp 내부 name속성과 이름이 동일해야함
     */
    //@NotBlank(message = "아이디를 입력하세요")
    // 메세지 코드를 이용하는 것이 좋음
    @NotBlank @Size(min = 6)
    private String id;
    @NotBlank
    private String name;
    @NotBlank @Size(min = 8)
    private String pw;
    @NotBlank
    private String pwcheck;
    @Email
    private String email;
//    private List<String> hobby; // String[] hobby, Set<String> hobby
    @AssertTrue
    private boolean agree;

//    private Address addr; // 중첩된 커맨드 객체 addr.address
}
