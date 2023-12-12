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
//        mv.addObject("message", )
    }
}
