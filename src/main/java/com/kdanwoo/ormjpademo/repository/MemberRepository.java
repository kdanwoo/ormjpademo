package com.kdanwoo.ormjpademo.repository;

import com.kdanwoo.ormjpademo.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList(); //객체를 대상으로 쿼리!
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

}
