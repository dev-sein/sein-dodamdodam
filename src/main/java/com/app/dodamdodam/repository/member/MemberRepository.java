package com.app.dodamdodam.repository.member;

import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
}
