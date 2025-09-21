package com.example.demo.services;

import com.example.demo.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    {
        products.add(new Product("qwerty1","PlayStation 5", "Simple description", 30000, "City1", "Author1"));
        products.add(new Product("qwerty2","Iphone 5", "Simple description", 5000, "City2", "Author2"));

    }
    public List<Product> getProducts(){
        return products;
    }
    public Product getProductById(String id){
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

}
