package com.app.dodamdodam.service.board.purchase;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.ProductDTO;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.domain.PurchaseFileDTO;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public interface PurchaseBoardService {
//    게시글 검색
    public Slice<PurchaseBoardDTO> getPurchaseBoardsWithSearch(PurchaseBoardSearch purchaseBoardSearch, Pageable pageable);

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
}
