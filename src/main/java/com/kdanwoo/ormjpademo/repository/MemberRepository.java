package com.kdanwoo.ormjpademo.repository;

import com.kdanwoo.ormjpademo.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    //Command랑 query를 분리해라. 굳이 저장하고 나서 영속객체를 가지고 다닐 필요가 없다.
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
