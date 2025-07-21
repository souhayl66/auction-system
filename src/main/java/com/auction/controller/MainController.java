package com.auction.controller;

import com.auction.model.Product;
import com.auction.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllActiveProducts();
        model.addAttribute("products", products);
        return "index";
    }
}