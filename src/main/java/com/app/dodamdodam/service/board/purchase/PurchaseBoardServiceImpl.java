package com.app.dodamdodam.service.board.purchase;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.file.purchase.PurchaseFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.product.ProductRepository;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.search.board.AdminPurchaseBoardSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("purchaseBoard") @Primary
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PurchaseBoardServiceImpl implements PurchaseBoardService {
    private final PurchaseBoardRepository purchaseBoardRepository;
    private final MemberRepository memberRepository;
    private final PurchaseFileRepository purchaseFileRepository;
    private final ProductRepository productRepository;


    /*게시글 등록*/
    @Override
    public void register(PurchaseBoardDTO purchaseBoardDTO, Long memberId) {
        ProductDTO productDTO = purchaseBoardDTO.getProductDTO();
        List<PurchaseFileDTO> purchaseFileDTOs = purchaseBoardDTO.getPurchaseFileDTOs();

        log.info("==============================");
        log.info("purchaseFileDTOs.toString()");
        log.info(purchaseFileDTOs.toString());
        log.info("==============================");

        memberRepository.findById(memberId).ifPresent(
                member -> purchaseBoardDTO.setMemberDTO(toMemberDTO(member))
        );

        Product product = toProductEntity(productDTO);

        log.info("product");
        log.info(product + "");

//        productRepository.save(product);

        PurchaseBoard purchaseBoard = purchaseBoardRepository.save(toPurchaseBoardEntity(purchaseBoardDTO));

        log.info(purchaseBoard.getId() + "");
//        Optional<PurchaseBoard> optionalPurchaseBoard = purchaseBoardRepository.findById(purchaseBoard.getId());
//        optionalPurchaseBoard.ifPresent(purchaseBoard1 -> log.info(purchaseBoard1.toString()));



        log.info("purchaseFileDTOs.size()");
        log.info(purchaseFileDTOs.size() + "");
        if(purchaseFileDTOs != null){
            for (int i = 0; i < purchaseFileDTOs.size(); i++) {
                PurchaseFile purchaseFile = toPurchaseFileEntity(purchaseFileDTOs.get(i));
                purchaseFile.setPurchaseBoard(purchaseBoard);

                log.info("purchaseFile.toString()");
                log.info(purchaseFile.toString());
                purchaseFileRepository.save(purchaseFile);
            }
        }

    }

    /*게시글 목록*/
    @Override
    public Slice<PurchaseBoardDTO> getPurchaseBoardsWithSearch(PurchaseBoardSearch purchaseBoardSearch, Pageable pageable) {
        Slice<PurchaseBoard> purchaseBoards = purchaseBoardRepository.findAllWithSearch_QueryDSL(purchaseBoardSearch, pageable);
        boolean hasNext = purchaseBoards.hasNext();

        List<PurchaseBoardDTO> purchaseBoardDTOS = purchaseBoards
                .stream().map(purchaseBoard -> toPurchaseBoardDTO(purchaseBoard)).collect(Collectors.toList());


        return new SliceImpl<>(purchaseBoardDTOS, pageable, hasNext);
    }

    /*게시글 조회*/
    @Override
    public PurchaseBoardDTO getPurchaseBoard(Long boardId){
        Optional<PurchaseBoard> optionalPurchaseBoard = purchaseBoardRepository.findPurchaseBoardById_QueryDSL(boardId);
        PurchaseBoardDTO purchaseBoardDTO = null;
        if (optionalPurchaseBoard.isPresent()){
            purchaseBoardDTO = toPurchaseBoardDTO(optionalPurchaseBoard.get());
        }
        return purchaseBoardDTO;
    }

    /* 내가 작성한 판매 게시글 목록 */
    @Override
    public List<PurchaseBoardFileDTO> getPurchaseBoardListByMemberId(Pageable pageable, Long memberId) {
        return purchaseBoardRepository.findPurchaseBoardListByMemberId_QueryDSL(pageable,memberId).stream()
                .map(purchaseBoard -> toPurchaseBoardFileDTO(purchaseBoard)).collect(Collectors.toList());
    }

    /* 관리자 검색 목록 */
    @Override
    public Page<PurchaseBoardDTO> findPurchaseBoardWithSearch_QueryDSL(Pageable pageable, AdminPurchaseBoardSearch adminPurchaseBoardSearch) {
        Page<PurchaseBoard> purchaseBoards = purchaseBoardRepository.findadminPurchaseSearchWithPaging_QueryDSL(adminPurchaseBoardSearch, pageable);
        List<PurchaseBoardDTO> purchaseBoardDTOS = purchaseBoards.getContent().stream()
                .map(this::toPurchaseBoardDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(purchaseBoardDTOS, pageable, purchaseBoards.getTotalElements());
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

    /* 메인 판매 게시글 최근 5개 */
    @Override
    public List<PurchaseBoardFileDTO> getRecentPurchaseBoardList() {
        List<PurchaseBoardFileDTO> purchaseBoardDTOs = purchaseBoardRepository.findRecentFreeBoardList_QueryDSL().stream().map(purchaseBoard -> toPurchaseBoardFileDTO(purchaseBoard)).collect(Collectors.toList());
        return purchaseBoardDTOs;
    }


}
