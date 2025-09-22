package com.example.demo.services;

import com.example.demo.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private long ID = 0;
    {
        products.add(new Product(ID++,"PlayStation 5", "Simple description", 30000, "City1", "Author1"));
        products.add(new Product(ID++,"Iphone 5", "Simple description", 5000, "City2", "Author2"));

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
        product.setId(ID++);
        products.add(product);
    }
    public void deleteProduct(Long id){
        products.removeIf(product -> product.getId().equals(id));
    }



}
