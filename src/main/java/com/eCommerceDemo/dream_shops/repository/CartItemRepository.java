package com.eCommerceDemo.dream_shops.repository;

import com.eCommerceDemo.dream_shops.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    void deleteAllByCartID(Long id);
}
