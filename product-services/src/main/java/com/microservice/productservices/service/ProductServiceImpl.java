package com.microservice.productservices.service;

import com.microservice.productservices.dto.ProductAddResponse;
import com.microservice.productservices.dto.ProductDTO;
import com.microservice.productservices.exception.ProjectException;
import com.microservice.productservices.feign.UserClient;
import com.microservice.productservices.mapper.ProductMapper;
import com.microservice.productservices.repository.ProductRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final UserClient userClient;
    private final ProductMapper productMapper;
    private static final String USER_NOT_FOUND ="User not found";

    @Override
    public ProductAddResponse addProductByUser(ProductDTO productDTO) {
        var user = userClient.getUserById(productDTO.getUserId());
        if (user == null) {
            throw new ProjectException(HttpStatus.NOT_FOUND,USER_NOT_FOUND);
        }
        var productJpa = productRepository.save(productMapper.fromProductDtoToProduct(productDTO));
        return ProductAddResponse.builder()
                .id(productJpa.getId())
                .name(productJpa.getName())
                .price(productJpa.getPrice())
                .userDTO(user)
                .build();
    }

    @Override
    public List<ProductDTO> getAllUsers() {
        return productRepository.findAll()
                .stream().map(productMapper::fromProductToProductDto).toList();
    }
}
