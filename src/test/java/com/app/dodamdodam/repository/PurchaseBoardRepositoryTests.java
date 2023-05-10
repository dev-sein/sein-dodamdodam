package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseFile;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.file.purchase.PurchaseFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.product.ProductRepository;
import com.app.dodamdodam.repository.purchase.PurchaseRepository;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.type.MemberStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class PurchaseBoardRepositoryTests {
    @Autowired
    private PurchaseBoardRepository purchaseBoardRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseFileRepository purchaseFileRepository;

    @Test
    public void saveTest(){
        Address address = new Address("1234", "test-address", "test-detail");
        Member member = new Member("test1234", "1234", "테스트", "test1234@gmail.com", "01012341234", address, MemberStatus.NORMAL);

        memberRepository.save(member);

        for (int i = 0; i < 10; i++) {


            Product product = new Product("테스트상품명" + i, 10000 + i, Long.valueOf(100 + i));

            productRepository.save(product);

            PurchaseBoard purchaseBoard = new PurchaseBoard("테스트제목" + i, "테스트내용" + i);
            purchaseBoard.setProduct(product);
            purchaseBoard.setMember(member);

            for (int j = 0; j < 5; j++) {
                PurchaseFile purchaseFile = new PurchaseFile("test" + (i+1) + ".png", UUID.randomUUID().toString(), "test" + (i+1), 1024L, purchaseBoard);
                purchaseFileRepository.save(purchaseFile);
            }

            purchaseBoardRepository.save(purchaseBoard);
        }

    }

    @Test
    public void findAllWithSearch_QueryDSLTest(){
        PurchaseBoardSearch purchaseBoardSearch = new PurchaseBoardSearch();
//        purchaseBoardSearch.setBoardTitle("테스트");

        Slice<PurchaseBoard> result = purchaseBoardRepository.findAllWithSearch_QueryDSL(purchaseBoardSearch, PageRequest.of(1, 5));

        result.stream().forEach(purchaseBoard -> log.info(purchaseBoard.toString()));
    }

    public void findAllTest(){
//        purchaseBoardRepository.findAll().stream().map(PurchaseBoard::toString).forEach(log::info);
        Pageable pageable = Pageable.ofSize(1);
        purchaseBoardRepository.findAllPurchaseBoard_QueryDSL(pageable);
    }

    @Test
    public void findByIdTest(){
        purchaseBoardRepository.findById(3L).ifPresent(purchaseBoard -> log.info(purchaseBoard.toString()));
    }

    @Test
    public void deleteTest(){
        purchaseBoardRepository.findById(3L).ifPresent(purchaseBoardRepository::delete);
    }



}
