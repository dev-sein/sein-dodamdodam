//package com.app.dodamdodam.domain;
//
//import com.app.dodamdodam.entity.chatting.Room;
//import com.app.dodamdodam.type.MessageType;
//import com.app.dodamdodam.type.ReadStatus;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.querydsl.core.annotations.QueryProjection;
//import com.sun.istack.NotNull;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import org.hibernate.annotations.ColumnDefault;
//
//import javax.persistence.*;
//
//@Data
//@Builder
//public class ChattingDTO {
//    public enum MessageType{
//        ENTER, TALK
//    }
//
//    private Long id;
//    private Long senderMemberId;
//    private Long receiverMemberId;
//    private String chattingContent;
//    private ReadStatus readStatus;
//    private MessageType messageType;
//    private RoomDTO roomDTO;
//
//    @QueryProjection
//    public ChattingDTO(Long id, Long senderMemberId, Long receiverMemberId, String chattingContent, ReadStatus readStatus, MessageType messageType, RoomDTO roomDTO) {
//        this.id = id;
//        this.senderMemberId = senderMemberId;
//        this.receiverMemberId = receiverMemberId;
//        this.chattingContent = chattingContent;
//        this.readStatus = readStatus;
//        this.messageType = messageType;
//        this.roomDTO = roomDTO;
//    }
//
////    @JsonCreator
////    public ChattingDTO(@JsonProperty("type") String type, @JsonProperty("sender") String sender) {}
//
//    public void setChattingContent(String chattingContent) {
//        this.chattingContent = chattingContent;
//    }
//}
