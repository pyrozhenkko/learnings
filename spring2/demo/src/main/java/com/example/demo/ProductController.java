package com.example.demo;

import com.example.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/")
    public String products(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "products";
    }
}
