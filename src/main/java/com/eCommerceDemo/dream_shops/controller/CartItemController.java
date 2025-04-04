package com.eCommerceDemo.dream_shops.controller;

import com.eCommerceDemo.dream_shops.exceptions.ResourceNotFoundException;
import com.eCommerceDemo.dream_shops.response.ApiResponse;
import com.eCommerceDemo.dream_shops.service.cartItem.ICartItemService;
import com.eCommerceDemo.dream_shops.service.cart.ICartService;
import com.eCommerceDemo.dream_shops.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")  // المسار الموحد
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;

    // إضافة عنصر إلى السلة
    @PostMapping("/item/add")
    public ResponseEntity<com.eCommerceDemo.dream_shops.response.ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId,
                                                                                            @RequestParam Long productId,
                                                                                            @RequestParam Integer quantity) {
        try {
            // إذا كانت السلة غير محددة، يتم إنشاء سلة جديدة
            if (cartId == null) {
                cartId = cartService.initializeNewCart();
            }
            cartItemService.addItemToCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Add Item Success", null));  // رسالة نجاح
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));  // رسالة خطأ
        }
    }

    // حذف عنصر من السلة
    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.ok(new ApiResponse("Remove Item Success", null));  // رسالة نجاح
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));  // رسالة خطأ
        }
    }

    // تحديث كمية عنصر في السلة
    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                          @PathVariable Long itemId,
                                                          @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Update Item Success", null));  // رسالة نجاح
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));  // رسالة خطأ
        }
    }
}
