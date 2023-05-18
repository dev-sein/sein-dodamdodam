package com.app.dodamdodam.service.board.freeBoard;

import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService {
    @Autowired
    private FreeBoardRepository freeBoardRepository;

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
    public Optional<FreeBoard> getFreeBoardById(Long boardId) {
        return freeBoardRepository.findFreeBoardAndFreeFilesById_QueryDSL(boardId);
    }

    /* 자유 게시글 수정 */
    @Override
    public void updateFreeBoard(FreeBoard board) {
        freeBoardRepository.findById(board.getId()).ifPresent(freeBoard -> {
            freeBoard.setBoardContent(board.getBoardContent());
            freeBoard.setBoardTitle(board.getBoardTitle());
            freeBoard.setFreeFiles(board.getFreeFiles());
        });
    }

    /* 자유 게시글 삭제 */
    @Override
    public void deleteFreeBoard(FreeBoard board) {
        freeBoardRepository.findById(board.getId()).ifPresent(freeBoard -> freeBoardRepository.delete(freeBoard));
    }

    /* 관리자 자유 게시판 목록 */
    @Override
    public Page<FreeBoardFileDTO> getAdminFreeBoardList(Pageable pageable) {
       Page<FreeBoard> freeBoardPage = freeBoardRepository.findAllFreeBoardList_QueryDSL(PageRequest.of(1, 10));
       List<FreeBoardFileDTO> freeBoardFileDTOS = freeBoardPage.get().map(this::toFreeBoardFileDTO).collect(Collectors.toList());
        return new PageImpl<>(freeBoardFileDTOS, pageable, freeBoardPage.getTotalElements());
    }

    /* 자유 게시글 Top5 */
    @Override
    public List<FreeBoardFileDTO> getTop5FreeBoards() {
        List<FreeBoard> freeBoards = freeBoardRepository.findFreeBoardListByLikeCount();
        List<FreeBoardFileDTO> freeBoardFileDTOS = freeBoards.stream().map(freeBoard -> toFreeBoardFileDTO(freeBoard)).collect(Collectors.toList());
        return freeBoardFileDTOS;
    }

    /* 자유 게시글 검색 */
    @Override
    public List<FreeBoardFileDTO> getFreeBoardsBySearch(Pageable pageable, FreeBoardSearch freeBoardSearch) {
        return freeBoardRepository.findFreeBoardBySearchWithPaging_QueryDSL(freeBoardSearch,pageable).stream().map(freeBoard -> toFreeBoardFileDTO(freeBoard)).collect(Collectors.toList());

    }
}
