package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//컨트롤러 애노테이션 마다
// 스프링 컨트롤러에서 스프링빈이 관리된다
@Controller
public class MemberController {
    private final MemberService memberService;

    //컨테이너에 있는 관리중인 멤버서비스를 스프링이 연결시켜줌
    //그러려면 서비스(+연계된 리포)에도 애노테이션이 기재되어 있어야
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /** 컴포넌트 스캔과 자동 의존관계 설정
     * 생성자를 통해 서비스를 넘겨받음으로 Dependency Injection 구현하였으며
     * 애노테이션 준수하여 자동화
     * 다만 애플리케이션이 소속된 패키지와 그 하위 패키지에 구성되어있어야 함
     */

    /**
     * 스프링빈에 한번 등록시켜놓으면 싱글톤으로 등록,관리되기 때문에 (아아주 특수한 케이스가 아니면)
     * 스프링 위에서 스프링빈이 되었다면 그 클래스는 단일 인스턴스로 존재하게 된다
     * ex> 멤버서비스, 주문서비스가 따로 있어서, 각각 멤버리포지토리를 호출해도 동일 인스턴스가 호출되는 것
     */
}
