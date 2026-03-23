package com.example.ecom.ecomApp.controller;

import com.example.ecom.ecomApp.product.Product;
import com.example.ecom.ecomApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){
        Product product=service.getById(id);
        if (product!=null) {
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProducts(@RequestPart Product product,@RequestPart MultipartFile imageFile){

       try{
           Product pro= service.addProduct(product,imageFile);
           return new ResponseEntity<>(pro,HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @GetMapping("/product/{pid}/image")
    public ResponseEntity<byte[]> getImgByPid(@PathVariable int pid){
        Product product=service.getById(pid);  //it gives product with id=pid
        //storing image of product with id=pid
        byte[] imgFile=product.getImgData();
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(product.getImgType()))
                .body(imgFile);

    }

}
