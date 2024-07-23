package com.microservice.productservices.controller;

import com.microservice.productservices.dto.ProductAddResponse;
import com.microservice.productservices.dto.ProductDTO;
import com.microservice.productservices.dto.UserDTO;
import com.microservice.productservices.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductAddResponse> createProductByUser(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.addProductByUser(productDTO));
    }

    @PostMapping("/async/add")
    @Transactional
    public ResponseEntity<ProductAddResponse> createAsyncProductByUser(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.addProductAsyncByUser(productDTO));
    }

    @GetMapping("/owner/{productId}")
    public ResponseEntity<UserDTO> getProductOwner(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductOwner(productId));
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId,@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(productId,productDTO));
    }

}
