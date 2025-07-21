package com.auction.controller;

import com.auction.model.User;
import com.auction.service.BidService;
import com.auction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;
    private final UserService userService;

    @PostMapping("/products/{productId}/bid")
    public String placeBid(@PathVariable Long productId, Authentication authentication) {
        String username = authentication.getName();
        User bidder = userService.findByUsername(username);
        bidService.placeBid(productId, bidder);
        return "redirect:/products/" + productId;
    }
}