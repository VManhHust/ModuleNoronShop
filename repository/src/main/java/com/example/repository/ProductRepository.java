package com.example.repository;

import NoronShop.jooq.data.tables.pojos.Product;
import NoronShop.jooq.data.tables.records.ProductRecord;
import org.jooq.Table;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import static NoronShop.jooq.data.tables.Product.PRODUCT;
@Component
public class ProductRepository extends AbstractCRUDRepository<ProductRecord,Integer, Product> {
    @Override
    protected Table<ProductRecord> table() {
        return PRODUCT;
    }
}
