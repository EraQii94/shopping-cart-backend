package com.eCommerceDemo.dream_shops.request;

import com.eCommerceDemo.dream_shops.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private Category category;
    private BigDecimal price;
    private int inventory;
    private String description;
}
