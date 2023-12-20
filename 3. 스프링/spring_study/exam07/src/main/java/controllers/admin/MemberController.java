package controllers.admin;

import lombok.RequiredArgsConstructor;
import models.member.Member;
import models.member.MemberDao;
import models.member.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller(value = "adminMemberController")
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberDao memberDao;

    @GetMapping // admin/member
    public String index(@ModelAttribute MemberSearch search, Errors errors, Model model){
        List<Member> members = memberDao.getList(search);
        model.addAttribute("members", members);
        members.stream().forEach(System.out::println);
        return "admin/member/list";
    }

    @GetMapping("/{id}")
    // 경로 변수명과 @PathVariable String 변수명이 동일하면 바로 됐는데
    // 업그레이드 후 따로 경로 변수명을 설정해 줘야한다.
    public String info(@PathVariable("id") String userId){
        System.out.println(userId);
        return "admin/member/info";
    }

    /**
     * 예외던지기
     */
    @GetMapping("/test")
    public String errorTest(){
        boolean result = true;
        if(result){
            //throw new RuntimeException("예외 발생!!"); // 500
            throw new MemberNotFoundException(); // 404 , 등록되지 않은 회원입니다
        }
        return "admin/member/info";
    }

    /**
     * 위 예외가 발생하면  이 메서드가 동작함
     * 주입하면 에러객체가 그대로 주입됨
     *
     *
     * 발생 예외를 정의
     * 예외 발생시 특정 페이지 노출
     * 메서드에 자동 주입
     *  - 발생한 예외 객체
     *  - Model
     *  - HttpServletRequest, HttpServletResponse, HttpSession
     *
     *  공통 에러의 경우 범위를 넓게 Exception을 설정해주는 것이 좋다.
     *
     * @ControllerAdvice -> 컨트롤러 실행 전 공통 기능
     *
     */
//    @ExceptionHandler({Exception.class})
//    public String errorHandler(Exception e, Model model){
//        e.printStackTrace();
//        model.addAttribute("message", e.getMessage());
//        return "error/common";
//    }
}
