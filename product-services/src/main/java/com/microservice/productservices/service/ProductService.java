package com.microservice.productservices.service;

import com.microservice.productservices.dto.UserDTO;
import com.microservice.productservices.entity.Product;
import com.microservice.productservices.feign.UserClient;
import com.microservice.productservices.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserClient userClient;

    public Product addProduct(Product product) {
        var user = userClient.getUserById(product.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return productRepository.save(product);
    }
}
