package com.microservice.productservices.controller;

import com.microservice.productservices.dto.ProductAddResponse;
import com.microservice.productservices.dto.ProductDTO;
import com.microservice.productservices.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductAddResponse> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.addProductByUser(productDTO));
    }

}
