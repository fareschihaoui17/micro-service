package com.microservice.productservices.service;

import com.microservice.productservices.dto.ProductAddResponse;
import com.microservice.productservices.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductAddResponse addProductByUser(ProductDTO productDTO);
    ProductAddResponse addProductAsyncByUser(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
}
