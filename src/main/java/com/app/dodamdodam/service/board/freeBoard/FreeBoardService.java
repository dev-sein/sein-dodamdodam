package com.app.dodamdodam.service.board.freeBoard;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeFile;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface FreeBoardService {
    /* 자유 게시글 전체 목록 */
    public List<FreeBoardFileDTO> getAllFreeBoards(Pageable pageable);
//    public Page<FreeBoard> getAllFreeBoards(Pageable pageable);

    /* 자유 게시글 카테고리 별 분류해서 가져오기 */
    public Page<FreeBoard> getFreeBoardsByCategoryType(CategoryType categoryType, Pageable pageable);

    /* 내가 작성한 자유게시글 목록 가져오기(memberId로 자유게시글 조회) */
    public List<FreeBoardFileDTO> getFreeBoardsByMemberId(Pageable pageable, Long memberId);

    /* 자유 게시글 상세 */
    public FreeBoardFileDTO getFreeBoardById(Long boardId);

    /* 자유 게시글 수정 */
    public void updateFreeBoard(FreeBoard updateBoard, Long boardId);

    /* 자유 게시글 삭제 */
    public void deleteFreeBoard(Long boardId);

    /* 자유게시글 좋아요 Top5 */
    public List<FreeBoardFileDTO> getTop5FreeBoards();

    /* 자유 게시글 검색 */
    public List<FreeBoardFileDTO> getFreeBoardsBySearch(Pageable pageable, CategoryType categoryType, FreeBoardSearch freeBoardSearch);

    /* 최근 작성된 자유 게시글 리스트 */
    public List<FreeBoardFileDTO> getRecentFreeBoardList();

    /* 좋아요 추가 */
    public void setLikeCountPlus(Long boardId, Long memberId);

    /* 좋아요 취소 */
    public void setLikeCountMinus(Long boardId, Long memberId);

    /* 좋아요 눌렀는지 체크 */
    public boolean checkFreeLikeByBoardIdAndMemberId(Long boardId, Long memberId);

    /* ========================== 관리자 ========================== */

    /* 관리자 자유 게시판 목록*/
    public Page<FreeBoardFileDTO> getAdminFreeBoardList(Pageable pageable);

    /* 관리자 자유게시판 삭제*/
    public void deleteAdminFreeBoard(List<Long> freeBoardIds);

    /* 관리자 자유게시판 상세보기 */
    public FreeBoardFileDTO getAdminFreeBoardDetail(Long id);

    /* 관리자 자유게시판 검색 */
    public Page<FreeBoardFileDTO> showAdminFreeWithSearch_QueryDSL(Pageable pageable, AdminFreeBoardSearch adminFreeBoardSearch);


    /* toDTO */
    default FreeBoardFileDTO toFreeBoardFileDTO(FreeBoard freeBoard){
        return FreeBoardFileDTO.builder().id(freeBoard.getId())
                .boardTitle(freeBoard.getBoardTitle())
                .boardContent(freeBoard.getBoardContent())
                .memberDTO(toMemberDTO(freeBoard.getMember()))
                .createdDate(freeBoard.getCreatedDate())
                .updatedDate(freeBoard.getUpdatedDate())
                .freeCategory(freeBoard.getFreeCategory())
                .likeCount(freeBoard.getLikeCount())
                .freeFileDTOS(
                        freeBoard.getFreeFiles().stream().map(e -> toFreeFileDTO(e)).collect(Collectors.toList())
                )
                .build();
    }

    default FreeBoardReplyDTO toFreeBoardReplyDTO(FreeBoard freeBoard){
        return FreeBoardReplyDTO.builder().id(freeBoard.getId())
                .boardTitle(freeBoard.getBoardTitle())
                .boardContent(freeBoard.getBoardContent())
                .freeReplyDTOS(
                        freeBoard.getFreeReplies().stream().map(freeReply -> toFreeReplyDTO(freeReply)).collect(Collectors.toList())
                )
                .build();
    }

    default FreeReplyDTO toFreeReplyDTO(FreeReply freeReply){
        return FreeReplyDTO.builder().id(freeReply.getId())
                .replyContent(freeReply.getReplyContent())
                .memberDTO(toMemberDTO(freeReply.getMember()))
                .build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder().id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberPhone(member.getMemberPhone())
                .memberStatus(member.getMemberStatus())
                .memberPoint(member.getMemberPoint())
                .participationCount(member.getParticipationCount())
                .address(member.getAddress())
                .memberType(member.getMemberType())
                .build();
    }

    default FreeFileDTO toFreeFileDTO(FreeFile freeFile){
        return FreeFileDTO.builder().id(freeFile.getId())
                .fileOriginalName(freeFile.getFileOriginalName())
                .filePath(freeFile.getFilePath())
                .fileUuid(freeFile.getFileUuid())
                .fileSize(freeFile.getFileSize())
                .build();
    }

}