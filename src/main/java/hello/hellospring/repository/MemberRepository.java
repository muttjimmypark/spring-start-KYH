package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//외부 db를 연동할수 있도록 인터페이스로 필요기능 구현
// 지금 예제에서는 간단히 메모리공간에서 테스트 하도록 구성
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
