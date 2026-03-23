package com.example.ecom.ecomApp.service;

import com.example.ecom.ecomApp.product.Product;
import com.example.ecom.ecomApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    public List<Product> getAllProducts() {

        return repository.findAll();
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImgName(image.getOriginalFilename());
        product.setImgType(image.getContentType());
        product.setImgData(image.getBytes());
        repository.save(product);
        return product;
    }

    public Product getById(int id) {
        return repository.findById(id).orElse(null);
    }
}
