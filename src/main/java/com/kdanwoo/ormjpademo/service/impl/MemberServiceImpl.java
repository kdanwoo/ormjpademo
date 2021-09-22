package com.kdanwoo.ormjpademo.service.impl;

import com.kdanwoo.ormjpademo.entity.Member;
import com.kdanwoo.ormjpademo.repository.MemberRepository;
import com.kdanwoo.ormjpademo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor //final 필드변수만 가진 생성자를 생성해주는 어노테이션
@Transactional(readOnly = true) //데이터의 변경 작업은 트랜잭션안에서 수행되어야 한다.
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입 기능
     * */
    @Override
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member); //영속성컨텍스트가 키를 pk관리하는거
        return member.getId(); //id값이 있다는 것을 보장해준다.
    }

    /**
     * 회원 목록 전체 조회 기능
     * */
    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Override
    @Transactional
    public void update(Long id, String name) {
        //jpa의 변경 감지 기능을 사용한다
        Member member = memberRepository.findOne(id);
        member.setName(name); //트랙잭션 종료시 -> commit이 된다. jpa flush
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
