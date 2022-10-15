package com.example.commons.data.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VendorRequest {
    private Integer userId;
    private String  handle;
    private String  status;
    private String  description;
    private Integer followCount;
    private Integer itemCount;
}
