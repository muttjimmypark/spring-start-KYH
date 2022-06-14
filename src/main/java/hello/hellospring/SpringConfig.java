package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    /**
     * 코드 작성하여 직접 스프링빈 등록하는 방법
     * 컨트롤러, 오토와이어는 그대로 두되, 서비스-리포지토리를 설정해줄수 있는 방법
     *
     * 실무에서 보통 애노테이션을 통한 자동 관계설정을 주로 사용하지만
     * 본 수업에서는 나중에 임시로 사용하던 메모리 리포지토리를 변경해야하므로
     * 직접 config 코드 작성하는 방법을 유지할것임
     */

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /**
     * 의존관계 설정 (=DI) 할때
     * 별개 필드 생성, setter 주입 할수도 있지만
     * 생성자 주입이 좋음
     *
     * 의존관계는 실행중에 동적으로 바뀌지 않음
     */
}
