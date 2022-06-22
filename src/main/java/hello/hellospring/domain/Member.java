package hello.hellospring.domain;

import javax.persistence.*;

/*
jpa를 활용하기 위한
entity 매핑 : ORM
 */
@Entity
public class Member {

    //db가 pk를 알아서 배정,관리 : IDENTITY
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
