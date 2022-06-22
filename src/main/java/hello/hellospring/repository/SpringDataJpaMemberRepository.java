package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
JpaRepository를 엔티티 클래스, pk 타입 쌍으로 구체화
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /*
    pk필드가 아니라서 통용될수 없는 비즈니스 로직들은
    아래처럼 약속에 맞춰 findBy00(Object 00) 하면
    JPQL로 select m from Member m where m.name = ?
    라고 자동작성시킬 수 있다

    복잡하게는 And나 Or를 통한 다중파람도 쓰일 수 있도록 지원된다

    -> 메서드 이름만으로 조회기능 제공
     */
    @Override
    Optional<Member> findByName(String name);
}
