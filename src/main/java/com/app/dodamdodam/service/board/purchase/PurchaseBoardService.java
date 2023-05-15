package com.app.dodamdodam.service.board.purchase;

import com.app.dodamdodam.domain.*;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.search.PurchaseBoardSearch;
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

    default PurchaseBoardDTO toPurchaseBoardDTO(PurchaseBoard purchaseBoard){
        return PurchaseBoardDTO.builder().id(purchaseBoard.getId())
                .boardTitle(purchaseBoard.getBoardTitle())
                .boardContent(purchaseBoard.getBoardContent())
                .memberDTO(toMemberDTO(purchaseBoard.getMember()))
                .productDTO(toProductDto(purchaseBoard.getProduct()))
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
                .recruitmentedCount(member.getParticipationCount())
                .address(member.getAddress())
                .build();
    }

    default ProductDTO toProductDto(Product product){
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

    default PurchaseBoardFileDTO toPurchaseBoardFileDto(PurchaseBoard purchaseBoard){
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
}
