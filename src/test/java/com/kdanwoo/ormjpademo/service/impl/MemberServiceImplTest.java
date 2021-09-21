package com.kdanwoo.ormjpademo.service.impl;

import com.kdanwoo.ormjpademo.entity.Member;
import com.kdanwoo.ormjpademo.repository.MemberRepository;
import com.kdanwoo.ormjpademo.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //Spring에서는 @Transactional 어노테이션이 테스트 케이스에 있으면 테스트 마치고 rollback 해버림.ㄴ
public class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false) //원래는 롤백되는데 눈으로 확인하고 싶은 경우에 체크 해둠!
    public void join() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //em.flush(); //디비에 쿼리로 나가는것이고! -> 트랜잭션이 rollback 하는 것임. 테스트는 반복되어야 하기 때문에
        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }
    
    @Test(expected = IllegalStateException.class)
    public void duplicatedMemberException() throws Exception{
        //given
         Member member1 = new Member();
         member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.

        //then
    }
}