package com.app.dodamdodam.service.board.purchase;

import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.domain.PurchaseBoardFileDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("purchaseBoard") @Primary
@RequiredArgsConstructor
public class PurchaseBoardServiceImpl implements PurchaseBoardService {
    private final PurchaseBoardRepository purchaseBoardRepository;

    @Override
    public Slice<PurchaseBoardDTO> getPurchaseBoardsWithSearch(PurchaseBoardSearch purchaseBoardSearch, Pageable pageable) {
        Slice<PurchaseBoard> purchaseBoards = purchaseBoardRepository.findAllWithSearch_QueryDSL(purchaseBoardSearch, pageable);
        boolean hasNext = purchaseBoards.hasNext();

        List<PurchaseBoardDTO> purchaseBoardDTOS = purchaseBoards
                .stream().map(purchaseBoard -> toPurchaseBoardDTO(purchaseBoard)).collect(Collectors.toList());


        return new SliceImpl<>(purchaseBoardDTOS, pageable, hasNext);
    }

    /* 내가 작성한 판매 게시글 목록 */
    @Override
    public List<PurchaseBoardFileDTO> getPurchaseBoardListByMemberId(Pageable pageable, Long memberId) {
        return purchaseBoardRepository.findPurchaseBoardListByMemberId_QueryDSL(pageable,memberId).stream()
                .map(purchaseBoard -> toPurchaseBoardFileDto(purchaseBoard)).collect(Collectors.toList());
    }

    /*관리자 목록 */
    @Override
    public Page<PurchaseBoardDTO> showList(Pageable pageable) {
        Page<PurchaseBoard> purchaseBoardPage = purchaseBoardRepository.findAllWithPaging(pageable);
        List<PurchaseBoardDTO> purchaseBoardDTOS = purchaseBoardPage.get().map(this::toPurchaseBoardDTO).collect(Collectors.toList());

        return new PageImpl<>(purchaseBoardDTOS, pageable, purchaseBoardPage.getTotalElements());
    }

    /* 관리자 판매 게시글 삭제 */
    @Override
    public void deleteAdminPurchaseBoard(List<Long> purchaseBoardIds) {
        for(Long purchaseBoardId: purchaseBoardIds){
            purchaseBoardRepository.deleteById(purchaseBoardId);
        }
    }

    /* 관리자 판매 게시글 상세 */
    @Override
    public PurchaseBoardDTO getAdminPurchaseBoardDetail(Long id) {
        Optional<PurchaseBoard> purchaseBoard = purchaseBoardRepository.findById(id);
        return toPurchaseBoardDTO(purchaseBoard.get());
    }


}
