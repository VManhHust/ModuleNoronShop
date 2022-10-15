package com.example.api.controller;

import com.example.api.service.ProductService;
import com.example.commons.data.request.ProductRequest;
import com.example.commons.data.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductRequest> saveProduct(@RequestBody ProductRequest productRequest) {
        productService.saveProduct(productRequest);
        return ResponseEntity.ok(productRequest);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    //
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductRequest> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable int id) {
        return productService.updateProduct(productRequest, id);
    }

    @GetMapping("/product/{id}/view")
    public ResponseEntity<String> upViewProduct(@PathVariable int id) {
        productService.upView(id);
        return ResponseEntity.ok("success");
    }
}
