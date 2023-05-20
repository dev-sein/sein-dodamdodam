package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FreeReplyDTO {
    private Long id;
    private MemberDTO memberDTO;
    private String replyContent;

    @QueryProjection
    public FreeReplyDTO(Long id, MemberDTO memberDTO, String replyContent) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.replyContent = replyContent;
    }
}
