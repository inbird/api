package hello.aaa.controller;

import hello.aaa.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            Model model
            ){
        log.info("from hello {}" , "init");
        Member member = new Member(name, age);
        model.addAttribute("member", member);

        return "hello";
    }
}
