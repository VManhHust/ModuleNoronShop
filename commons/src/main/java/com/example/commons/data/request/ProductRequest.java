package com.example.commons.data.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductRequest {

    private String handle;

    private String product_title;

    private String product_type;

    private String product_description;

    private String short_description;

    private int origin_price;

    private int discount;

    private int final_price;

    private int origin_coin;

    private int final_coin;

    private int num_buy;

    private int num_review;

    private int rating;

    private Long vendorId;

    private Long categoryId;

}
