package com.example.ecom.ecomApp.service;

import com.example.ecom.ecomApp.product.Product;
import com.example.ecom.ecomApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    public List<Product> getAllProducts() {

        return repository.findAll();
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public Product getById(int id) {
        return repository.findById(id).orElse(null);
    }
}
