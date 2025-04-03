package com.eCommerceDemo.dream_shops.controller;


import com.eCommerceDemo.dream_shops.exceptions.ResourceNotFoundException;
import com.eCommerceDemo.dream_shops.response.ApiResponse;
import com.eCommerceDemo.dream_shops.service.cart.ICartService;
import com.eCommerceDemo.dream_shops.service.cartItem.ICartItemService;
import jdk.javadoc.doclet.Reporter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.valves.rewrite.ResolverImpl;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
//@RestController
//@RequestMapping("/${api.prefix}/cartItem")
//public class CartItemController {
//    private final ICartItemService cartItemService;
//    private final ICartService cartService;
//
//
//
//    @PostMapping("/item/add")
//    private ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId,
//                                                      @RequestParam Long productId,
//                                                      @RequestParam Integer quantity){
//
//        try {
//            if (cartId==null){
//                cartId = cartService.initializeNewCart();
//            }
//            cartItemService.addCartItem(cartId, productId, quantity);
//            return ResponseEntity.ok(new ApiResponse("Item added to cart successfully", true));
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(NOT_FOUND)
//                    .body(new ApiResponse(e.getMessage(), false));
//        }
//    }

@RestController
@RequestMapping("/${api.prefix}/cartItem")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId,
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity) {
        try {
            if (cartId == null) {
                cartId = cartService.initializeNewCart();
            }
            cartItemService.addCartItem(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item added to cart successfully", true));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), false));
        }
    }
    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.removeItemFromCart(cartId,itemId);
            return ResponseEntity.ok(new ApiResponse("Item removed from cart successfully", true));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }






    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item quantity updated successfully", true));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }



}




//    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
//    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
//        try {
//            cartItemService.removeItemFromCart(cartId,itemId);
//            return ResponseEntity.ok(new ApiResponse("Item removed from cart successfully", true));
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
//        }
//    }
//
//
//
//
//
//
//    @PutMapping("/cart/{cartId}/item/{itemId}/update")
//    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam Integer quantity) {
//        try {
//            cartItemService.updateItemQuantity(cartId, itemId, quantity);
//            return ResponseEntity.ok(new ApiResponse("Item quantity updated successfully", true));
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
//        }
//    }
//
//





