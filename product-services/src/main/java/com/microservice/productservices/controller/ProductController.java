package com.microservice.productservices.controller;

import com.microservice.productservices.entity.Product;
import com.microservice.productservices.repository.ProductRepository;
import com.microservice.productservices.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

}
