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
@RequiredArgsConstructor
@Transactional(readOnly = true) //데이터의 변경 작업은 트랜잭션안에서 수행되어야 한다.
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입 기능
     * */
    @Override
    @Transactional(readOnly = true)
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

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
