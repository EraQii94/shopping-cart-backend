package com.eCommerceDemo.dream_shops.service.cartItem;

import com.eCommerceDemo.dream_shops.exceptions.ResourceNotFoundException;
import com.eCommerceDemo.dream_shops.model.Cart;
import com.eCommerceDemo.dream_shops.model.CartItem;
import com.eCommerceDemo.dream_shops.model.Product;
import com.eCommerceDemo.dream_shops.repository.CartItemRepository;
import com.eCommerceDemo.dream_shops.repository.CartRepository;
import com.eCommerceDemo.dream_shops.service.cart.CartService;
import com.eCommerceDemo.dream_shops.service.cart.ICartService;
import com.eCommerceDemo.dream_shops.service.product.IProductService;
import com.eCommerceDemo.dream_shops.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;
    private final ICartService cartService;

    @Override
    public void addCartItem(Long cartId, Long productId, int quantity) {
        //1. get the cart
        //2. get the product
        //3. check if the item already oin the cart
        //4. if yes, increase the quantity with your requested quantity
        //5. if no, initiate new cart item entry

        Cart cart = cartService.getCart(cartId);
        Product product= productService.getProductById(productId);

        CartItem cartItem =cart.getItems()
                .stream()
                .filter(item-> item.getProduct().equals(productId))
                .findFirst()
                .orElse(new CartItem());

        if (cartItem.getId() == null){
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setTotalPrice();
        } else {
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        }

        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
    }


    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = cart.getItems()
                .stream()
                .filter(item-> item.getProduct().getId().equals(productId)).findFirst()
                .orElseThrow(()-> new ResourceNotFoundException("Item not found in the cart"));

        cart.removeItem(itemToRemove);
        cartRepository.save(cart);
    }



    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {

        Cart cart = cartService.getCart(cartId);
        cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().
                ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());
                    item.setTotalPrice();

                });
    }
}
