package com.example.commons.data.mapper;

import NoronShop.jooq.data.tables.pojos.Vendor;
import com.example.commons.data.response.VendorResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorResponse vendorToResponse(Vendor vendor);

    List<VendorResponse> vendorsToResponses(List<Vendor> vendors);

}
