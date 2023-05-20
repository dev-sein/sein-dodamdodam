package com.app.dodamdodam.repository.reply.freeReply;

import com.app.dodamdodam.entity.free.FreeReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeReplyRepository extends JpaRepository<FreeReply, Long>, FreeReplyQueryDsl {
}