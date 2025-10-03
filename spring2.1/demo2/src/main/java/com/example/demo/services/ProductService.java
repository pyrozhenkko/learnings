package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private List<Product> products = new ArrayList<>();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List <Product> listProducts(String title) {
        List<Product> products = productRepository.findByTitle(title);
        if (title != null) productRepository.findByTitle(title);

        return productRepository.findAll();
    }

    public List<Product> getProducts(){
        return products;
    }
    public Product getProductById(Long id){
        for (Product product : products) {
            if(product.getId().equals(id)){
                return product;
            }
        }
        throw new NoSuchElementException("Product with id " + id + " not found");
    }
    public void addProduct(Product product){
        products.add(product);
    }
    public void deleteProduct(Long id){
        products.removeIf(product -> product.getId().equals(id));
    }



}
