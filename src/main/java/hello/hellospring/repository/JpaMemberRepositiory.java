package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepositiory implements MemberRepository {

    /*
    빌드정보, db정보 등을 모두 짬뽕해서
    스프링부트가 알아서 생성해줌
     */
    private final EntityManager em;

    public JpaMemberRepositiory(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }


    /*
    단껀 처리가 아닌 경우 jpql이라는걸 작성해줘야함
    다음 차수에서 스프링데이터jpa를 활용하면 이 쿼리도 안작성해도 됨
    */
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        /*
        쿼리랑 비슷한데 select문이 Member객체 자체를 셀렉하는걸 볼수 있다
         */
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }
}
