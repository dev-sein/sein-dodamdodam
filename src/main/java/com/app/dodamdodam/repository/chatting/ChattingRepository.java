package com.app.dodamdodam.repository.chatting;

import com.app.dodamdodam.entity.chatting.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

public interface ChattingRepository extends JpaRepository<Chatting, Long>, ChattingQueryDsl {
}
