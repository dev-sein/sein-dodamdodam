package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
    //관리자 홈
    public List<Member> findAllByOrderByIdDesc();
}
