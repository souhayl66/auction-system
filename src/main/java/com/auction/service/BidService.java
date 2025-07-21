package com.auction.service;

import com.auction.model.Bid;
import com.auction.model.Product;
import com.auction.model.User;
import com.auction.repository.BidRepository;
import com.auction.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    private final ProductRepository productRepository;

    public Bid placeBid(Long productId, User bidder) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getAuctionEndDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Auction has ended");
        }

        double newBidPrice = product.getCurrentPrice() * 1.10; // 10% increase

        Bid bid = new Bid();
        bid.setDate(LocalDateTime.now());
        bid.setPrice(newBidPrice);
        bid.setStatus(Bid.BidStatus.ACTIVE);
        bid.setBidder(bidder);
        bid.setProduct(product);

        product.setCurrentPrice(newBidPrice);
        productRepository.save(product);

        return bidRepository.save(bid);
    }

    public List<Bid> getBidsByProduct(Long productId) {
        return bidRepository.findByProductIdOrderByPriceDesc(productId);
    }

    public List<Bid> getBidsByUser(Long userId) {
        return bidRepository.findByBidderId(userId);
    }
}