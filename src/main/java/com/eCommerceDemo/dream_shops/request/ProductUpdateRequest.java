package com.eCommerceDemo.dream_shops.request;

import com.eCommerceDemo.dream_shops.model.Category;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
