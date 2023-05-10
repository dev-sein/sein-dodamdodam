package com.app.dodamdodam.service.member;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;

    /* 로그인 된 유저 정보 */
    @Override
    public Optional<Member> getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
