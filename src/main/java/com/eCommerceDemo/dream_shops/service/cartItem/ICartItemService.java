package com.eCommerceDemo.dream_shops.service.cartItem;

import com.eCommerceDemo.dream_shops.model.CartItem;

public interface ICartItemService {
    void addCartItem(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);
}
