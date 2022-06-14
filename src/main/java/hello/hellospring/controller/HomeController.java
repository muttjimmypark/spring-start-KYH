package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 컨테이너에 root로 매핑되어 올라갈 것임
     * 컨테이너에 root로 배정된 것이 없으면, 그제서 static 패키지의 index를 사용하게 되는 것
     */

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
