package com.microservice.productservices.service;

import com.microservice.productservices.dto.ProductAddResponse;
import com.microservice.productservices.dto.ProductDTO;
import com.microservice.productservices.dto.UserDTO;
import java.util.List;

public interface ProductService {
    ProductAddResponse addProductByUser(ProductDTO productDTO);
    ProductAddResponse addProductAsyncByUser(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    UserDTO getProductOwner(Long productId);
    ProductDTO getProductById(Long productId);
    ProductDTO updateProduct(Long productId,ProductDTO productDTO);
    void deleteProduct(Long productId);
}
