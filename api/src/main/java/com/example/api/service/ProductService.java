package com.example.api.service;


import NoronShop.jooq.data.tables.pojos.Product;
import NoronShop.jooq.data.tables.pojos.Vendor;
import com.example.commons.data.mapper.ProductMapper;
import com.example.commons.data.request.ProductRequest;
import com.example.commons.data.response.ProductResponse;
import com.example.commons.dto.ActionLogDTO;
import com.example.repository.ProductRepository;
import com.example.repository.VendorRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.commons.utils.CollectionUtils.extractFieldToSet;
import static com.example.commons.utils.TimeUtils.getCurrentTimeLong;

@Service

public class ProductService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final VendorRepository vendorRepository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    public ProductService(KafkaTemplate<String, Object> kafkaTemplate,
                          ProductRepository productRepository,
                          VendorRepository vendorRepository,
                          ProductMapper productMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.productMapper = productMapper;
    }
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        Set<Integer> vendorIds = extractFieldToSet(products, Product::getVendorid);
        Map<Integer, Vendor> vendorMap = vendorRepository.getVendors(vendorIds);
        return productMapper.toResponses(products, vendorMap);
    }

    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id);
        Vendor vendor = vendorRepository.findById(product.getVendorid());
        return productMapper.toResponse(product, vendor);
    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product product = productRepository.insert(productMapper.toPojo(productRequest));
        Vendor vendor = vendorRepository.findById(product.getVendorid());
        return productMapper.toResponse(product, vendor);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    //
    public ResponseEntity<ProductRequest> updateProduct(ProductRequest productRequest, int id) {
        Product temp = productRepository.findById(id);
        if (temp == null) return ResponseEntity.noContent().build();

        Product product = productMapper.toPojo(productRequest);
        productRepository.update(id, product);
        return ResponseEntity.ok(productRequest);
    }

    public void upView(int id) {
        ActionLogDTO productDto = new ActionLogDTO()
                .setObjectType("product")
                .setObjectId(id + "")
                .setCreatedAt(getCurrentTimeLong());
        Object message = "upview" + id;

        // json -> productDto -> string, jackson:
        kafkaTemplate.send("first_topic", message);
    }
}
