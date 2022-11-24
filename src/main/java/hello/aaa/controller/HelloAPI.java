package hello.aaa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.aaa.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class HelloAPI {
    @RequestMapping("/hello/api")
    public String helloAPI() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Member member = new Member("Hong", 35);

        String result = mapper.writeValueAsString(member);

        log.info("json string : {}", result);
        return result;
    }

    @RequestMapping("/hello/api2")
    public String helloAPI2() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Member member1 = new Member("Hong", 35);
        Member member2 = new Member("KIM", 20);
        Member member3 = new Member("KANG", 29);

        List<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        members.add(member3);

        String result = mapper.writeValueAsString(members);

        log.info("json string : {}", result);
        return result;
    }

}
