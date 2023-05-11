package com.app.dodamdodam.repository.reply.eventReply;

import com.app.dodamdodam.entity.event.EventReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReplyRepository extends JpaRepository<EventReply, Long>,EventReplyQueryDsl {
}
