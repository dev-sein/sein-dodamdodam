package com.app.dodamdodam.repository.chatting;

import com.app.dodamdodam.entity.chatting.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting, Long>, ChattingQueryDsl {
}
