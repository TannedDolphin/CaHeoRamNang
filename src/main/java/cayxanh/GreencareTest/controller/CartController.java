package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.Cart;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Lấy tất cả các giỏ hàng
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Lấy giỏ hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable int id) {
        Cart cart = cartService.getCart(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Thêm giỏ hàng mới
    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody User user) {
        Cart newCart = cartService.addCart(user);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    // Xóa giỏ hàng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
        boolean isDeleted = cartService.deleteCart(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
