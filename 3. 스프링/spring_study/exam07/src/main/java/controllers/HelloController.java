package controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false, value="name", defaultValue = "기본값") String name, Model model){

        model.addAttribute("msg", "안녕?");
        model.addAttribute("name", name);
        return "hello";
    }

//    @GetMapping("/hello")
//    public String hello(@RequestParam("name") String name){
//        System.out.println(name);
//        return "hello";
//    }
//    @GetMapping("/hello")
//    public ModelAndView hello(){
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("message", "안녕하세요"); // 담을 객체
//        mv.setViewName("hello"); // 출력할 뷰 네임, /WEB-INF/templates/hello.jsp
//        return mv;
//    }


    /**
     * HadlerAdapter 가 아래의 객체를 알아서 자동주입해준다.
     */

//    @GetMapping("/hello")
//    public String hello(HttpServletRequest requset, HttpServletResponse response, HttpSession session) {
//        System.out.println("request : " + requset);
//        System.out.println("reponse : " + response);
//        System.out.println("session : " + session);
//        return "hello";
//    }
}
