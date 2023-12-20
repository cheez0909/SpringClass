package org.choongang.controllers.member;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 회원 관련 컨드롤러
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/join")
    public String join(){
        return "member/join";
    }
}
