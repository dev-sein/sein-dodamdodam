package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.type.MessageType;
import com.app.dodamdodam.type.ReadStatus;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Builder
public class ChattingDTO {
    private Long id;
    private Long senderMemberId;
    private Long receiverMemberId;
    private String chattingContent;
    private ReadStatus readStatus;
    private MessageType messageType;
    private Integer chattingUnreadCount;

    @QueryProjection
    public ChattingDTO(Long id, Long senderMemberId, Long receiverMemberId, String chattingContent, ReadStatus readStatus, MessageType messageType, Integer chattingUnreadCount) {
        this.id = id;
        this.senderMemberId = senderMemberId;
        this.receiverMemberId = receiverMemberId;
        this.chattingContent = chattingContent;
        this.readStatus = readStatus;
        this.messageType = messageType;
        this.chattingUnreadCount = chattingUnreadCount;
    }
}
