package com.eCommerceDemo.dream_shops.controller;


import com.eCommerceDemo.dream_shops.dto.ProductDto;
import com.eCommerceDemo.dream_shops.exceptions.ResourceNotFoundException;
import com.eCommerceDemo.dream_shops.model.Product;
import com.eCommerceDemo.dream_shops.request.AddProductRequest;
import com.eCommerceDemo.dream_shops.request.ProductUpdateRequest;
import com.eCommerceDemo.dream_shops.response.ApiResponse;
import com.eCommerceDemo.dream_shops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor //avoids me from making a constructor
@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {
    @Autowired
    private final IProductService productService;





    @GetMapping("/all")
    public ResponseEntity<ApiResponse> GetAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.convertToProduct(products);
        return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
    }





    @GetMapping("/product/{id}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
        try {
            Product product = productService.getProductById(id);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Found!", productDto));
        } catch (Exception e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try {
            Product product1 = productService.addProduct(product);
            ProductDto productDto = productService.convertToDto(product1);
            return ResponseEntity.ok(new ApiResponse("Product added successfully", productDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }




    @PutMapping("/product/{id}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest product, @PathVariable Long id){
        try {
            Product product1 = productService.updateProduct(product, id);
            ProductDto productDto = productService.convertToDto(product1);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }




    @DeleteMapping("/product/{id}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }




    @GetMapping("/product/by/brandNaMe-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
        try {
            List<Product> products = productService.getProductByBrandAndName(brandName, productName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProducts = productService.convertToProduct(products);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception E){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(E.getMessage(), null));
        }
    }



    @GetMapping("/product/by/category-and-brandNaMe")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String categoryName, @RequestParam String brandName){
        try {
            List<Product> products = productService.getProductByCategoryAndBrand(categoryName, brandName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProducts = productService.convertToProduct(products);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception E){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(E.getMessage(), null));
        }
    }



    @GetMapping("/product/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String productName){
        try {
            List<Product> products = productService.getProductByName(productName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProducts = productService.convertToProduct(products);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception E){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(E.getMessage(), null));
        }
    }




    @GetMapping("product/by-brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand){
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProducts = productService.convertToProduct(products);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception E){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(E.getMessage(), null));
        }
    }




    @GetMapping("/product/{categoryName}/products")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category){
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found", null));
            }
            List<ProductDto> convertedProducts = productService.convertToProduct(products);
            return ResponseEntity.ok(new ApiResponse("Found!", convertedProducts));
        } catch (Exception E){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(E.getMessage(), null));
        }
    }



    @GetMapping("/product/count/by-brandNaMe/and-name")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
        try {
            var count = productService.countProductsByBrandAndName(brandName, productName);
            return ResponseEntity.ok(new ApiResponse("Found!", count));
        } catch (Exception E){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(E.getMessage(), null));
        }
    }






































}
