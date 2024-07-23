package com.microservice.productservices.service;

import com.microservice.productservices.dto.ProductAddResponse;
import com.microservice.productservices.dto.ProductDTO;
import com.microservice.productservices.exception.ProjectException;
import com.microservice.productservices.feign.UserClient;
import com.microservice.productservices.mapper.ProductMapper;
import com.microservice.productservices.repository.ProductRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final UserClient userClient;
    private final ProductMapper productMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String USER_NOT_FOUND = "User not found";
    private static final String TOPIC = "product-topic";

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
    public ProductAddResponse addProductAsyncByUser(ProductDTO productDTO) {
        log.info("Adding product for user with ID: {}", productDTO.getUserId());
        var user = userClient.getUserById(productDTO.getUserId());

        if (user == null) {
            log.error(USER_NOT_FOUND);
            throw new ProjectException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
        }

        var productJpa = productRepository.save(productMapper.fromProductDtoToProduct(productDTO));
        var productAddResponse = ProductAddResponse.builder()
                .id(productJpa.getId())
                .name(productJpa.getName())
                .price(productJpa.getPrice())
                .userDTO(user)
                .build();

        kafkaTemplate.send(TOPIC, "Product added successfully");
        log.info("Sent message to Kafka topic: {}", TOPIC);

        return productAddResponse;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream().map(productMapper::fromProductToProductDto).toList();
    }
}
