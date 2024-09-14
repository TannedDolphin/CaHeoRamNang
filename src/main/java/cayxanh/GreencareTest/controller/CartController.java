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


}
