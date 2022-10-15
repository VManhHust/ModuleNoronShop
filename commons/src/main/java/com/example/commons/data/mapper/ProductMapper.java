package com.example.commons.data.mapper;

import NoronShop.jooq.data.tables.pojos.Product;
import NoronShop.jooq.data.tables.pojos.Vendor;
import com.example.commons.data.request.ProductRequest;
import com.example.commons.data.response.ProductResponse;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    protected VendorMapper vendorMapper;

    @Mapping(source = "title", target = "product_title")
    @Mapping(source = "type", target = "product_type")
    @Mapping(source = "description", target = "product_description")
    public abstract ProductResponse toResponse(Product product);

    @Mapping(source = "title", target = "product_title")
    @Mapping(source = "type", target = "product_type")
    @Mapping(source = "description", target = "product_description")
    public abstract ProductResponse toResponse(Product product, @Context Vendor vendor);

    @Mapping(source = "product_title", target = "title")
    @Mapping(source = "product_type", target = "type")
    @Mapping(source = "product_description", target = "description")
    public abstract Product toPojo(ProductRequest productRequest);

    public List<ProductResponse> toResponses(List<Product> products, @Context Map<Integer, Vendor> vendorMap) {
        return products.stream()
                .map(product -> {
                    ProductResponse productResponse = toResponse(product);
                    Vendor vendor = vendorMap.getOrDefault(product.getVendorid(), null);
                    productResponse.setVendor(vendorMapper.vendorToResponse(vendor));
                    return productResponse;
                })
                .collect(Collectors.toList());
    }

    @AfterMapping
    protected void afterToResponse(@MappingTarget ProductResponse response, Product product, @Context Vendor vendor) {
        //
        response.setVendor(vendorMapper.vendorToResponse(vendor));
    }
}
