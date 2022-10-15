package com.example.repository;

import NoronShop.jooq.data.tables.pojos.Vendor;
import NoronShop.jooq.data.tables.records.VendorRecord;
import org.jooq.Table;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

import static NoronShop.jooq.data.tables.Vendor.VENDOR;

@Component
public class VendorRepository extends AbstractCRUDRepository<VendorRecord, Integer, Vendor> {

    @Override
    protected Table<VendorRecord> table() {
        return VENDOR;
    }
    @Override
    protected VendorRecord getRecord() {
        return new VendorRecord();
    }

    public Map<Integer, Vendor> getVendors(Set<Integer> vendorIds) {
        return dslContext.selectFrom(VENDOR)
                .where(VENDOR.ID.in(vendorIds))
                .fetchMap(VENDOR.ID, Vendor.class);
    }
}
