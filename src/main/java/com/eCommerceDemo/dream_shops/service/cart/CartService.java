package com.eCommerceDemo.dream_shops.service.cart;

import com.eCommerceDemo.dream_shops.exceptions.ResourceNotFoundException;
import com.eCommerceDemo.dream_shops.model.Cart;
import com.eCommerceDemo.dream_shops.repository.CartItemRepository;
import com.eCommerceDemo.dream_shops.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;


@Service
@RequiredArgsConstructor
public class CartService implements ICartService{
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }


    @Transactional
    @Override
    public Long initializeNewCart() {
        Cart cart = new Cart();
        // إعداد البيانات المبدئية
        Cart savedCart = cartRepository.save(cart);  // تأكد أن cart جديد وليس موجود مسبقًا
        return savedCart.getId();
    }



}
