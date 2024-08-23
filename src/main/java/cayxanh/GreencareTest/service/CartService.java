package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Cart;
import cayxanh.GreencareTest.repo.CartRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepo cartRepo;
    public Cart getCart(int id) {
        return cartRepo.findById(id).orElseThrow(()->new RuntimeException("No cart found with id " + id));
    }
    public List<Cart> getAllCarts() {
        List<Cart> carts = cartRepo.findAll();
        if (carts.isEmpty()) {
            throw new RuntimeException("No cart found");
        }
        return carts;
    }
    public Cart addCart(Cart cart) {
        Cart newCart = cartRepo.save(cart);
        if (newCart == null) {
            throw new RuntimeException("No cart found");
        }
        return newCart;
    }
    public boolean deleteCart(int id) {
        Cart cart = cartRepo.findById(id).orElseThrow(()->new RuntimeException("No cart found with id " + id));
        cartRepo.delete(cart);
        return true;
    }
}
