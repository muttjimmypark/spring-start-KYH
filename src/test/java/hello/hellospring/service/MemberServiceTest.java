package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void tearDown() {
        memberRepository.clearStore();
    }

    //given-when-then 문법이 기초적인 틀
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //then
        //수업에서 제시한 try-catch
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage()).isEqualTo(MemberService.ERROR_DUPLICATED_NAME);
//        }
        //수업에서 제시한 assertThrows (TDD에서 배웠으나 아래 방법을 권장했었다)
        assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));

        //TDD 수업에서 배운것 적용
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}