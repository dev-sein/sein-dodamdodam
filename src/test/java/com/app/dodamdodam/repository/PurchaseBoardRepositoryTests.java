package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.product.ProductRepository;
import com.app.dodamdodam.repository.purchase.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void saveTest(){
        Member member = new Member("test1234", "1234", "테스트", "test1234@gmail.com", "01012341234", "testaddress", "testdetail");

        memberRepository.save(member);

        for (int i = 0; i < 10; i++) {
            Product product = new Product("테스트상품명" + i, 10000 + i, Long.valueOf(100 + i));

            productRepository.save(product);

            PurchaseBoard purchaseBoard = new PurchaseBoard("테스트제목" + i, "테스트내용" + i);
            purchaseBoard.setProduct(product);
            purchaseBoard.setMember(member);

            purchaseBoardRepository.save(purchaseBoard);
        }

    }

    @Test
    public void findAllTest(){
//        purchaseBoardRepository.findAll().stream().map(PurchaseBoard::toString).forEach(log::info);
        Pageable pageable = Pageable.ofSize(1);
        purchaseBoardRepository.findAllPurchaseBoard(pageable);
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
