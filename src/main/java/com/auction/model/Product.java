package com.auction.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private double initialPrice;
    private double currentPrice;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime auctionEndDate;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "product")
    private List<Bid> bids;

    public enum Category {
        ELECTRONICS, FURNITURE, CLOTHING, ART, COLLECTIBLES, OTHER
    }
}