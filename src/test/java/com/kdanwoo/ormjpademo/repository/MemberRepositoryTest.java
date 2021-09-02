package com.kdanwoo.ormjpademo.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

//    @Test
//    @Transactional //Spring에서는 @Transactional 어노테이션이 테스트 케이스에 있으면 테스트 마치고 rollback 해버림.
//    @Rollback(false) //Rollback 안하는거임!
//    public void testMember() throws Exception{
//        //given
//        Member member = new Member();
//        member.setUsername("memberA");
//
//        //when
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);
//
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//
//        //같은 트랜잭션안에서 id값이 같을 경우 영속성컨텍스트에서 1개로 관리되어진다. 1차캐시!
//        Assertions.assertThat(findMember).isEqualTo(member);
//
//    }
}