package com.eCommerceDemo.dream_shops.controller;


import com.eCommerceDemo.dream_shops.exceptions.AlreadyExistException;
import com.eCommerceDemo.dream_shops.exceptions.ResourceNotFoundException;
import com.eCommerceDemo.dream_shops.model.Category;
import com.eCommerceDemo.dream_shops.response.ApiResponse;
import com.eCommerceDemo.dream_shops.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor //avoids me from making a constructor
@RestController
@RequestMapping("${api.prefix}/category")
public class CategoryController {

    //field injection
    private final ICategoryService categoryService;




    @GetMapping("/categories/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories= categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found!", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", INTERNAL_SERVER_ERROR));
        }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        try {
            Category category1= categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Category added successfully!", category1));
        } catch (AlreadyExistException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Category already exists!", CONFLICT));
        }
    }






    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category found!", category));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Category not found!", NOT_FOUND));
        }

    }






    @GetMapping("/category/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        try {
            Category category = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Category found!", category));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Category not found!", NOT_FOUND));
        }

    }




    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Category deleted!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }





    @PutMapping("/category/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category){
        try {
            Category updatedCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("update Success!", updatedCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Category not found!", NOT_FOUND));
        }

    }





















}
