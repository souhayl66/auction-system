package com.auction.repository;

import com.auction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByAuctionEndDateAfter(LocalDateTime now);
    List<Product> findBySellerId(Long sellerId);
}