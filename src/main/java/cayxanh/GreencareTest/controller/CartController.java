package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.dto.request.CreateCartRequest;
import cayxanh.GreencareTest.entity.Cart;
import cayxanh.GreencareTest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody CreateCartRequest request) {
        Cart cart = cartService.createCart(request);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Integer id, @RequestBody CreateCartRequest request) {
        Cart cart = cartService.updateCart(id, request);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }
}
