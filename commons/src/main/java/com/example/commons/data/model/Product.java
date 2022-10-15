package com.example.commons.data.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String handle;

    private String title;

    private String type;

    private String description;

    private String short_description;

    private int origin_price;

    private Integer discount;

    private Integer final_price;

    private Integer origin_coin;

    private Integer final_coin;

    private Integer num_buy;

    private Integer num_review;

    private Integer rating;

    private Long vendorId = null;

    private Long categoryId = null;

    private Date created_at;
}
