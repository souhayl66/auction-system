package com.auction.repository;

import com.auction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByProductIdOrderByPriceDesc(Long productId);
    List<Bid> findByBidderId(Long bidderId);
    Bid findTopByProductIdOrderByPriceDesc(Long productId);
}