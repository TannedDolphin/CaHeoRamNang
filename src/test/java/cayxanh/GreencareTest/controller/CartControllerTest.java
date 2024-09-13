package cayxanh.GreencareTest.controller;

import cayxanh.GreencareTest.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCarts() {
        List<Cart> carts = Arrays.asList(new Cart(), new Cart());
        when(cartService.getAllCarts()).thenReturn(carts);

        ResponseEntity<List<Cart>> response = cartController.getAllCarts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carts, response.getBody());
    }

    @Test
    public void testGetCartById() {
        Cart cart = new Cart();
        when(cartService.getCart(1)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.getCartById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testAddCart() {
        User user = new User();
        Cart cart = new Cart();
        when(cartService.addCart(user)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.addCart(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testDeleteCart() {
        when(cartService.deleteCart(1)).thenReturn(true);

        ResponseEntity<Void> response = cartController.deleteCart(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cartService, times(1)).deleteCart(1);
    }

    @Test
    public void testDeleteCartNotFound() {
        when(cartService.deleteCart(1)).thenReturn(false);

        ResponseEntity<Void> response = cartController.deleteCart(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(cartService, times(1)).deleteCart(1);
    }
}
