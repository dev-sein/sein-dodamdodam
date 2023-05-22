package com.app.dodamdodam.service.board.purchase;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.inquiry.Inquiry;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.search.Inquiry.AdminInquirySearch;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.search.board.AdminPurchaseBoardSearch;
import com.app.dodamdodam.search.board.AdminRecruitmentSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public interface PurchaseBoardService {
//    게시글 검색
    public Slice<PurchaseBoardDTO> getPurchaseBoardsWithSearch(PurchaseBoardSearch purchaseBoardSearch, Pageable pageable);

//    내가 작성한 판매 게시글 목록
    public List<PurchaseBoardFileDTO> getPurchaseBoardListByMemberId(Pageable pageable, Long memberId);

//    저장
    public void register(PurchaseBoardDTO purchaseBoardDTO, Long memberId);

//    관리자 게시글 검색
    public Page<PurchaseBoardDTO> findPurchaseBoardWithSearch_QueryDSL(Pageable pageable, AdminPurchaseBoardSearch adminPurchaseBoardSearch);

    //관리자 : 판매 게시판 목록
    public Page<PurchaseBoardDTO> showList(Pageable pageable);

    //관리자 : 판매 게시판 삭제
    public void deleteAdminPurchaseBoard(List<Long> purchaseBoardIds);

    //관리자 : 판매 게시판 상세
    public PurchaseBoardDTO getAdminPurchaseBoardDetail(Long id);

    default PurchaseBoardDTO toPurchaseBoardDTO(PurchaseBoard purchaseBoard){
        return PurchaseBoardDTO.builder().id(purchaseBoard.getId())
                .boardTitle(purchaseBoard.getBoardTitle())
                .boardContent(purchaseBoard.getBoardContent())
                .memberDTO(toMemberDTO(purchaseBoard.getMember()))
                .productDTO(toProductDTO(purchaseBoard.getProduct()))
                .purchaseFileDTOs(
                        purchaseBoard.getPurchaseFiles().stream().map(e -> toPurchaseFileDTO(e)).collect(Collectors.toList())
                )
                .createdDate(purchaseBoard.getCreatedDate())
                .updatedDate(purchaseBoard.getUpdatedDate())

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

    default ProductDTO toProductDTO(Product product){
        return ProductDTO.builder().id(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productCount(product.getProductCount())
                .build();
    }

    default PurchaseFileDTO toPurchaseFileDTO(PurchaseFile purchaseFile){
        return PurchaseFileDTO.builder().id(purchaseFile.getId())
                .fileOriginalName(purchaseFile.getFileOriginalName())
                .filePath(purchaseFile.getFilePath())
                .fileUuid(purchaseFile.getFileUuid())
                .fileSize(purchaseFile.getFileSize())
                .build();
    }

    default PurchaseBoardFileDTO toPurchaseBoardFileDTO(PurchaseBoard purchaseBoard){
        return PurchaseBoardFileDTO.builder()
                .boardContent(purchaseBoard.getBoardContent())
                .boardTitle(purchaseBoard.getBoardTitle())
                .createdDate(purchaseBoard.getCreatedDate())
                .updatedDate(purchaseBoard.getUpdatedDate())
                .id(purchaseBoard.getId())
                .memberDTO(toMemberDTO(purchaseBoard.getMember()))
                .purchaseFileDTOS(purchaseBoard.getPurchaseFiles().stream().map(purchaseFile -> toPurchaseFileDTO(purchaseFile)).collect(Collectors.toList()))
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder().id(memberDTO.getId())
                .memberId(memberDTO.getMemberId())
                .memberPassword(memberDTO.getMemberPassword())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberPhone(memberDTO.getMemberPhone())
                .memberStatus(memberDTO.getMemberStatus())
                .memberRole(memberDTO.getMemberRole())
                .memberType(memberDTO.getMemberType())
                .address(memberDTO.getAddress())
                .build();
    }
    default Product toProductEntity(ProductDTO productDTO) {
        return Product.builder().id(productDTO.getId())
                .productName(productDTO.getProductName())
                .productPrice(productDTO.getProductPrice())
                .productCount(productDTO.getProductCount())
                .build();
    }
    default PurchaseBoard toPurchaseBoardEntity(PurchaseBoardDTO purchaseBoardDTO){
        return PurchaseBoard.builder().id(purchaseBoardDTO.getId())
                .boardTitle(purchaseBoardDTO.getBoardTitle())
                .boardContent(purchaseBoardDTO.getBoardContent())
                .member(toMemberEntity(purchaseBoardDTO.getMemberDTO()))
                .product(toProductEntity(purchaseBoardDTO.getProductDTO()))
                .build();
    }

    default PurchaseFile toPurchaseFileEntity(PurchaseFileDTO purchaseFileDTO){
        return PurchaseFile.builder().id(purchaseFileDTO.getId())
                .fileOriginalName(purchaseFileDTO.getFileOriginalName())
                .fileUuid(purchaseFileDTO.getFileUuid())
                .filePath(purchaseFileDTO.getFilePath())
                .fileSize(purchaseFileDTO.getFileSize())
                .build();
    }


}
