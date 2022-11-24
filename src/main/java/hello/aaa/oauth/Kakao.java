package hello.aaa.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Slf4j
@Controller
public class Kakao {

    @Autowired
    private KakaoService kakaoService;

    @RequestMapping("/oauth/kakao")
    public String kakaoTest(String code) throws Exception{
        log.info("code={}", code);
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        log.info( "access_token : {} ", access_Token);
        log.info("name, email : {}, {}", userInfo.get("nickName"), userInfo.get("email"));

        return "result";
    }
}
