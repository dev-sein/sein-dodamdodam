package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
    //관리자 홈
    public List<Member> findAllByOrderByIdDesc();
    //    아이디로 전체 정보 조회 (MemberDetailService)
    public Optional<Member> findByMemberId(String memberId);
    //    이메일로 전체 정보 조회 (MemberService)
    public Optional<Member> findByMemberEmail(String memberEmail);
}
