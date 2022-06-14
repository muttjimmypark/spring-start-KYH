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
     * 컨트롤러, 오토와이어는 그대로 두되, 서비스-리포지토리를 설정해줄수 있는 방
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
     */
}
