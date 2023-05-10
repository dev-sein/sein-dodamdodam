package com.app.dodamdodam.service.member;

import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService {
    /* 로그인 된 유저 정보 가져오기 */
    public Optional<Member> getMemberInfo(Long memberId);


}
