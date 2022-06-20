package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    /**
     * localhost:8080/hello라는 주소에 매핑되어서, 스프링부트/톰캣이 리퀘스트를 받음
     * hello라는 템플릿에게 요구사항을 리턴할 컨트롤러
     * 해당 템플릿을 활용하여 /hello라는 요청에 응답
     */



    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    /**
     * MVC와 템플릿 엔진을 사용하는 방법
     *
     * 마찬가지로 로:8080/hello-mvc라는 주소에 매핑되어서
     * 부트/톰캣이 리퀘스트를 받으면
     * hello-template이라는 템플릿에게 리턴할 컨트롤러
     * 그 템플릿을 활용해서 /hello-mvc라는 요청에 응답
     *
     * 템플릿에 전달하는 viewResolver가 사용됨
     */



    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        //html 태그없이 그대로 data만 내려주는 방식 : 페이지 소스를 열면 hello name만 보임
    }
    /**
     * API 사용 1
     * @ResponseBody 애노테이션으로 템플릿 없이 본 내용이
     * 리퀘스트에 대한 직접 응답이라고 매핑
     * 이 응답은 HTTP의 BODY에 실려 전달됨 (html body 태그 아님)
     * (http는 header와 body로 구성되어있으며, 현시점에서 body는 그냥 html태그 그 자체라고 봐도 될듯 (css, js, and so on) )
     */



    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

        //http 통신의 body부분에 실어 보내겠다는 애노테이션
        //스프링 컨테이너가 view에서 가공하지 않고, 객체를 json돌려서 그대로 http응답에 실어보냄
        //json 구조가 그대로 노출되어 화면출력 {"name":"spring"}
        //json -> xml등 마크업랭귀지의 대안이라 주류가 된것
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    /**
     * API 사용 2
     * 이렇게 특정 객체를 정의하고 그 상태를 리턴할 수도 있다
     * 지금은 엄청 간단하게 구현한것
     * 크로스플랫폼식의 웹앱 동작에서 쓰인다고하는데
     */
}
