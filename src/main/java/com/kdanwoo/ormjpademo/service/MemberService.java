package com.kdanwoo.ormjpademo.service;

import com.kdanwoo.ormjpademo.entity.Member;

import java.util.List;

public interface MemberService {
    //회원가입 기능
    Long join(Member member);
    //회원전체조회 기능
    List<Member> findMembers();
    Member findOne(Long id);

    void update(Long id, String name);
}
