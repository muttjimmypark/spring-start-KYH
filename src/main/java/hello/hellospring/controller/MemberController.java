package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
     * 애노테이션 준수하여 자동화 (Autowired)
     * 다만 애플리케이션이 소속된 패키지와 그 하위 패키지에 구성되어있어야 함
     */

    /**
     * 스프링빈에 한번 등록시켜놓으면 싱글톤으로 등록,관리되기 때문에 (아아주 특수한 케이스가 아니면)
     * 스프링 위에서 스프링빈이 되었다면 그 클래스는 단일 인스턴스로 존재하게 된다
     * ex> 멤버서비스, 주문서비스가 따로 있어서, 각각 멤버리포지토리를 호출해도 동일 인스턴스가 호출되는 것
     */


    /**
     * 템플릿을 리턴하여 해당 주소에 매핑
     */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
    /**
     * post 방식으로 폼이 넘어와서 매핑됨
     * <form action="/members/new" method="post">
     *
     * 이름이 name인 필드에서 입력값을 name이라는 변수로 전달
     * <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
     *
     * 필드-변수가 여러쌍인 경우, 여기서의 MemberForm에서 여러 값을 불러다 작업할수 있는건가?
     */



    /**
     * 위의 넘겨받는 예제와 비교해서 살펴볼것
     * 아래는 서비스가 동작한 결과가 템플릿 리턴할때 들어가니까
     */
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    /**
     * members가 html.thymeLeaf의 기능을 통해 - forEach처럼 루프를 돈다
     *      <tr th:each="member : ${members}">
     *         <td th:text="${member.id}"></td>
     *         <td th:text="${member.name}"></td>
     *       </tr>
     */
}
