package com.eCommerceDemo.dream_shops.service.cart;

import com.eCommerceDemo.dream_shops.model.Cart;
import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);


    Long initializeNewCart();
}
