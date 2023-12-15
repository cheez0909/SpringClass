package controllers.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final JoinValidator joinValidator;

//    @GetMapping("/member")
//    public String join(){
//        return "/member/Join";
//    }

    /**
     * form 데이터를 담는 객체와
     * form name을 동일하게 입력하게 되면
     * 매개변수에 폼 객체만 선언했을때
     * 입력한 데이터가 그대로 들어온다
     */
    @PostMapping("/join")
    // Errors errors도 매개변수에 추가 가능
    public String joinPs(@Valid RequestJoin requestJoin, Errors errors, Model model){
        // 에러 객체는 커맨드 객체 뒤쪽에 위치해야한다.

        joinValidator.validate(requestJoin, errors);
        /**
         * 검증 실패 시
         */
        if(errors.hasErrors()){
            return "member/Join";
        }

        // 회원가입에 실패했을 때 기존 값이 날아가면 안되므로
        // 모델에 커멘드 객체를 담고 뷰에서 value값을 설정해준다
        // 하지만 이러한 반복작업을 줄이기 위해서 커맨드객체에는
        // requestJoin으로 속성이 추가된다
        // 그래서 템플릿 내에서 바로 접근이 가능하다.
        // model.addAttribute("requestjoin", request);
        System.out.println(requestJoin);
        // response.sendRedirect.getContextPath() + "/member/login"
        // Location : 주소
//        return "redirect:/member/login";

        // 출력 버퍼만 바뀜 주소이동이 아님
        // 데이터가 그대로 이동함
        return "forward:/member/login";
    }

    /**
     * 스프링 내에서 공유하는 데이터(현재 URL / MEMBER)
     *
     */
    @ModelAttribute("hobbies")
    public List<String> hobbyList(){
        return Arrays.asList("자바", "오라클", "JSP", "스프링");
    }

    @GetMapping("/login")
    public String login(RequestJoin requestJoin, Model model){
        model.addAttribute("requestJoin", requestJoin);
        return "member/login";
    }

//    @GetMapping("/login")
//    public String login(@RequestParam("userId") String id, @RequestParam("userNm") String name, Model model){
//        model.addAttribute("id", id);
//        model.addAttribute("name", name);
//        return "member/login";
//    }

    @PostMapping("/login")
    public String loginPs(RequestLogin requestLogin){
        System.out.println(requestLogin);
        return "member/login";
    }

    /**
     * 매핑되었을때
     * 스타일시트 적용시키기
     */
    // @GetMapping("/member/join")
    // @RequestMapping(path = "/member/join", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin requestJoin,  Model model){

        // 스타일시트 적용시키기
//        String[] addCss = {"member/style1", "member/style2"};
//        List<String> addScript = Arrays.asList("member/script1", "member/script2");
//        model.addAttribute("addCss", addCss);
//        model.addAttribute("addScript", addScript);
        //model.addAttribute("requestJoin", new RequestJoin());// = @ModelAttribute RequestJoin requestJoin와 같음
        model.addAttribute("pageTitle", "회원가입");
        return "member/Join";
    }

    @GetMapping("/list")
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
