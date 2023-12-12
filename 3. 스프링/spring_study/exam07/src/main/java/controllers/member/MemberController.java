package controllers.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {


    @GetMapping("/member")
    public String join(){
        return "/member/Join";
    }

    /**
     * form 데이터를 담는 객체와
     * form name을 동일하게 입력하게 되면
     * 매개변수에 폼 객체만 선언했을때
     * 입력한 데이터가 그대로 들어온다
     */
    @PostMapping("/member")
    public String joinPs(RequestJoin requestJoin){
        System.out.println(requestJoin);
        return "member/Join";
    }

    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String loginPs(RequestLogin requestLogin){
        System.out.println(requestLogin);
        return "member/login";
    }
}
