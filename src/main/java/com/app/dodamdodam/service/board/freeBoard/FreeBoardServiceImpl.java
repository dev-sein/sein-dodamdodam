package com.app.dodamdodam.service.board.freeBoard;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeFile;
import com.app.dodamdodam.entity.free.FreeLike;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.free.like.FreeBoardLikeRepository;
import com.app.dodamdodam.repository.file.freeFile.FreeFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FreeBoardServiceImpl implements FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;
    private final FreeBoardLikeRepository freeBoardLikeRepository;
    private final FreeFileRepository freeFileRepository;
    private final MemberRepository memberRepository;

    /* 자유 게시글 전체 목록 */
    @Override
    public List<FreeBoardFileDTO> getAllFreeBoards(Pageable pageable) {
        return freeBoardRepository.findAllFreeBoardList_QueryDSL(pageable)
                .stream().map(freeBoard -> toFreeBoardFileDTO(freeBoard)).collect(Collectors.toList());
    }

    /* 자유 게시글 카테고리 별 분류해서 가져오기 */
    @Override
    public Page<FreeBoard> getFreeBoardsByCategoryType(CategoryType categoryType, Pageable pageable) {
        return freeBoardRepository.findFreeBoardListByCategoryType_QueryDSL(pageable, categoryType);
    }

    /* 내가 작성한 자유 게시글 가져오기 */
    @Override
    public List<FreeBoardFileDTO> getFreeBoardsByMemberId(Pageable pageable, Long memberId) {
        return freeBoardRepository.findFreeBoardListByMemberId_QueryDSL(pageable, memberId)
                .stream().map(freeBoard -> toFreeBoardFileDTO(freeBoard)).collect(Collectors.toList());
    }

    /* 자유 게시글 상세 */
    @Override
    public FreeBoardFileDTO getFreeBoardById(Long boardId) {
        FreeBoardFileDTO freeBoardFileDTO = toFreeBoardFileDTO(freeBoardRepository.findFreeBoardAndFreeFilesById_QueryDSL(boardId).get());
        return freeBoardFileDTO;
    }

    /* 자유 게시글 수정 */
    @Override
    public void updateFreeBoard(FreeBoard updatedBoard, Long boardId) {
        freeBoardRepository.findById(boardId).ifPresent(freeBoard -> {
            freeBoard.setBoardContent(updatedBoard.getBoardContent());
            freeBoard.setBoardTitle(updatedBoard.getBoardTitle());
            freeBoard.setFreeFiles(updatedBoard.getFreeFiles());
        });
    }

    /* 자유 게시글 삭제 */
    @Override
    public void deleteFreeBoard(Long boardId) {
        freeBoardRepository.findById(boardId).ifPresent(freeBoard -> freeBoardRepository.delete(freeBoard));
    }

    /* 자유 게시글 저장 */
    @Override
    public void register(FreeBoardDTO freeBoardDTO, Long memberId) {
        List<FreeFileDTO> freeFileDTOS = freeBoardDTO.getFreeFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> freeBoardDTO.setMemberDTO(toMemberDTO(member))
        );
        FreeBoard freeBoard = freeBoardRepository.save(toFreeBoardEntity(freeBoardDTO));

        log.info(freeFileDTOS.size() + "");
        if(freeFileDTOS != null){
            for (int i = 0; i < freeFileDTOS.size(); i++) {
                FreeFileDTO freeFileDTO = freeFileDTOS.get(i);
                FreeFile freeFile = toFreeFileEntity(freeFileDTO);
                freeFile.setFreeBoard(freeBoard);

                freeFileRepository.save(freeFile);
            }
        }

    }


    /* 자유 게시글 Top5 */
    @Override
    public List<FreeBoardFileDTO> getTop5FreeBoards() {
        List<FreeBoard> freeBoards = freeBoardRepository.findFreeBoardListByLikeCount_QueryDSL();
        List<FreeBoardFileDTO> freeBoardFileDTOS = freeBoards.stream().map(freeBoard -> toFreeBoardFileDTO(freeBoard)).collect(Collectors.toList());
        return freeBoardFileDTOS;
    }

    /* 자유 게시글 검색 */
    @Override
    public List<FreeBoardFileDTO> getFreeBoardsBySearch(Pageable pageable, CategoryType categoryType ,FreeBoardSearch freeBoardSearch) {
        return freeBoardRepository.findFreeBoardBySearchWithPaging_QueryDSL(freeBoardSearch, categoryType, pageable).stream().map(freeBoard -> toFreeBoardFileDTO(freeBoard)).collect(Collectors.toList());
    }

    /* 최근 작성된 자유 게시글 리스트 */
    @Override
    public List<FreeBoardFileDTO> getRecentFreeBoardList() {
        List<FreeBoardFileDTO> freeBoardFileDTOS = freeBoardRepository.findRecentFreeBoardList_QueryDSL().stream().map(freeBoard -> toFreeBoardFileDTO(freeBoard)).collect(Collectors.toList());
        return freeBoardFileDTOS;
    }

    /* 좋아요 추가 */
    public void setLikeCountPlus(Long boardId, Long memberId){
        FreeLike freeLike = new FreeLike(memberId,freeBoardRepository.findById(boardId).get());
        freeBoardLikeRepository.save(freeLike);
        freeBoardRepository.findById(boardId).get().updateLikePlus();
    }

    /* 좋아요 취소 */
    public void setLikeCountMinus(Long boardId, Long memberId){
        freeBoardLikeRepository.deleteByBoardIdAndMemberId_QueryDSL(boardId,memberId);
        freeBoardRepository.findById(boardId).get().updateLikeMinus();
    }

    /* 좋아요 눌렀는지 체크 */
    @Override
    public boolean checkFreeLikeByBoardIdAndMemberId(Long boardId, Long memberId) {
        return freeBoardLikeRepository.checkLikeByBoardIdAndMemberId_QueryDSL(boardId,memberId);
    }




    /*====================== 관리자 ======================*/

    /* 관리자 자유 게시글 목록 */
    @Override
    public Page<FreeBoardFileDTO> getAdminFreeBoardList(Pageable pageable) {
        Page<FreeBoard> freeBoardPage = freeBoardRepository.findAllFreeBoardList_QueryDSL(pageable);
        List<FreeBoardFileDTO> freeBoardFileDTOS = freeBoardPage.get().map(this::toFreeBoardFileDTO).collect(Collectors.toList());
        return new PageImpl<>(freeBoardFileDTOS, pageable, freeBoardPage.getTotalElements());
    }

    /* 관리자 자유 게시글 삭제*/
    @Override
    public void deleteAdminFreeBoard(List<Long> freeBoardIds) {
        for(Long freeBoardId: freeBoardIds){
            freeBoardRepository.deleteById(freeBoardId);
        }
    }

    /* 관리자 자유 게시글 상세보기 */
    @Override
    public FreeBoardFileDTO getAdminFreeBoardDetail(Long id) {
        Optional<FreeBoard> freeBoard = freeBoardRepository.findById(id);
        return toFreeBoardFileDTO(freeBoard.get());
    }

    /* 관리자 자유 게시글 검색*/
    @Override
    public Page<FreeBoardFileDTO> showAdminFreeWithSearch_QueryDSL(Pageable pageable, AdminFreeBoardSearch adminFreeBoardSearch) {
        Page<FreeBoard> freeBoardPage = freeBoardRepository.findAdmindFreeBoardWithPaging_QueryDSL(adminFreeBoardSearch, pageable);
        List<FreeBoardFileDTO> freeBoardFileDTOS = freeBoardPage.getContent().stream()
                .map(this::toFreeBoardFileDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(freeBoardFileDTOS, pageable, freeBoardPage.getTotalElements());
    }

}