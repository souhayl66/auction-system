package com.auction.service;

import com.auction.model.Product;
import com.auction.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        product.setCurrentPrice(product.getInitialPrice());
        product.setAuctionEndDate(LocalDateTime.now().plusDays(3));
        return productRepository.save(product);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findByAuctionEndDateAfter(LocalDateTime.now());
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
}