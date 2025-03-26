package com.eCommerceDemo.dream_shops.repository;

import com.eCommerceDemo.dream_shops.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
