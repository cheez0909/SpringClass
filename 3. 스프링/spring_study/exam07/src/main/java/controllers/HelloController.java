package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

//    @GetMapping("/hello")
//    public String hello(@RequestParam("name") String name){
//        System.out.println(name);
//        return "hello";
//    }
    @GetMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "안녕하세요"); // 담을 객체
        mv.setViewName("hello"); // 출력할 뷰 네임, /WEB-INF/templates/hello.jsp
        return mv;
    }
}
