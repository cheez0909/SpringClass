package controllers.admin;

import lombok.RequiredArgsConstructor;
import models.member.Member;
import models.member.MemberDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller(value = "adminMemberController")
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberDao memberDao;

    @GetMapping // admin/member
    public String index(@ModelAttribute MemberSearch search, Errors errors, Model model){
        // List<Member> members = memberDao.getList(search);
        // model.addAttribute("members", members);
        return "admin/member/list";
    }

    @GetMapping("/{id}")
    // 경로 변수명과 @PathVariable String 변수명이 동일하면 바로 됐는데
    // 업그레이드 후 따로 경로 변수명을 설정해 줘야한다.
    public String info(@PathVariable("id") String userId){
        System.out.println(userId);
        return "admin/member/info";
    }
}
