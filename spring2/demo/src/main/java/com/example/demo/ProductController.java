package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;

public class ProductController {
    @GetMapping("/")
    public String products() {
        return "products";
    }
}
