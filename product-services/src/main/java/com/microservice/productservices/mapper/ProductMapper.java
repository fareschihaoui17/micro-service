package com.microservice.productservices.mapper;

import com.microservice.productservices.dto.ProductDTO;
import com.microservice.productservices.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product fromProductDtoToProduct(ProductDTO productDTO);
    ProductDTO fromProductToProductDto(Product product);
}
