package com.eCommerceDemo.dream_shops.service.product;

import com.eCommerceDemo.dream_shops.model.Product;
import com.eCommerceDemo.dream_shops.request.AddProductRequest;
import com.eCommerceDemo.dream_shops.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long productId);
    void deleteProduct(Long productId);
    Product updateProduct(ProductUpdateRequest request, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductByCategoryAndBrand(String category, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String Brand, String name);

}
