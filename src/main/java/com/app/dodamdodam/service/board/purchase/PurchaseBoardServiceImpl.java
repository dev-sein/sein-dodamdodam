package com.app.dodamdodam.service.board.purchase;

import com.app.dodamdodam.domain.ProductDTO;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.domain.PurchaseBoardFileDTO;
import com.app.dodamdodam.domain.PurchaseFileDTO;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.file.purchase.PurchaseFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("purchaseBoard") @Primary
@RequiredArgsConstructor
@Transactional
public class PurchaseBoardServiceImpl implements PurchaseBoardService {
    private final PurchaseBoardRepository purchaseBoardRepository;
    private final MemberRepository memberRepository;
    private final PurchaseFileRepository purchaseFileRepository;

    @Override
    public void register(PurchaseBoardDTO purchaseBoardDTO, ProductDTO productDTO, Long memberId) {
        List<PurchaseFileDTO> purchaseFileDTOs = purchaseBoardDTO.getPurchaseFileDTOs();

        memberRepository.findById(memberId).ifPresent(
                member -> purchaseBoardDTO.setMemberDTO(toMemberDTO(member))
        );

        purchaseBoardRepository.save(toPurchaseBoardEntity(purchaseBoardDTO));
        if(purchaseFileDTOs != null){
            for (int i = 0; i < purchaseFileDTOs.size(); i++) {
                purchaseFileRepository.save(toPurchaseFileEntity(purchaseFileDTOs.get(i)));
            }
        }
    }

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
                .map(purchaseBoard -> toPurchaseBoardFileDTO(purchaseBoard)).collect(Collectors.toList());
    }




    @Override
    public Page<PurchaseBoardDTO> showList(Pageable pageable) {
        Page<PurchaseBoard> purchaseBoardPage = purchaseBoardRepository.findAllWithPaging(PageRequest.of(1,10));
        List<PurchaseBoardDTO> purchaseBoardDTOS = purchaseBoardPage.get().map(this::toPurchaseBoardDTO).collect(Collectors.toList());

        return new PageImpl<>(purchaseBoardDTOS, pageable, purchaseBoardPage.getTotalElements());
    }


}
