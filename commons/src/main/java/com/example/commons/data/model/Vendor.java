package com.example.commons.data.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Vendor {
    private Integer id;
    private Integer userId;
    private String  handle;
    private String  status;
    private String  description;
    private Integer followCount;
}
