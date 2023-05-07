package com.app.dodamdodam.repository.product;

import com.app.dodamdodam.entity.purchase.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}