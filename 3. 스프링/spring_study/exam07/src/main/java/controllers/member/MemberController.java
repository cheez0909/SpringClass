package controllers.member;

import lombok.extern.slf4j.Slf4j;
import models.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
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
    public String login(@RequestParam("userId") String id, @RequestParam("userNm") String name, Model model){
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "member/login";
    }

    @PostMapping("/member/login")
    public String loginPs(RequestLogin requestLogin){
        System.out.println(requestLogin);
        return "member/login";
    }

    /**
     * 매핑되었을때
     * 스타일시트 적용시키기
     */
    @GetMapping("/member/join")
    public String join(Model model){

        String[] addCss = {"member/style1", "member/style2"};
        List<String> addScript = Arrays.asList("member/script1", "member/script2");
        model.addAttribute("addCss", addCss);
        model.addAttribute("addScript", addScript);
        model.addAttribute("pageTitle", "회원가입");
        return "member/Join";
    }

    @GetMapping("/member/list")
    public String members(Model model){
        List<Member> members = new ArrayList<>();
        for(int i=1; i<=10; i++){
            Member member = Member.builder()
                    .userNo(Long.valueOf(i)).userId("1234" + i)
                    .userPw("1234").userNm("사용자01" + i)
                    .regDt(LocalDateTime.now()).email("uesr01" + i + "@test.org").build();
            members.add(member);
        }
        model.addAttribute("members", members);
        return "member/list";
    }
}
