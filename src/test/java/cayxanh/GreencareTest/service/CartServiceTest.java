package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.entity.Cart;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.CartRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepo cartRepo;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCartSuccess() {
        Cart cart = new Cart();
        cart.setId(1);

        when(cartRepo.findById(1)).thenReturn(Optional.of(cart));

        Cart result = cartService.getCart(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testGetCartNotFound() {
        when(cartRepo.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cartService.getCart(1);
        });

        assertEquals("No cart found with id 1", exception.getMessage());
    }

    @Test
    public void testGetAllCartsSuccess() {
        Cart cart = new Cart();
        when(cartRepo.findAll()).thenReturn(Collections.singletonList(cart));

        List<Cart> result = cartService.getAllCarts();

        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetAllCartsEmpty() {
        when(cartRepo.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cartService.getAllCarts();
        });

        assertEquals("No cart found", exception.getMessage());
    }

    @Test
    public void testAddCart() {
        User user = new User();
        Cart cart = new Cart();
        cart.setUser(user);

        when(cartRepo.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.addCart(user);

        assertNotNull(result);
        assertEquals(user, result.getUser());
    }

    @Test
    public void testDeleteCartSuccess() {
        Cart cart = new Cart();
        cart.setId(1);

        when(cartRepo.findById(1)).thenReturn(Optional.of(cart));

        boolean result = cartService.deleteCart(1);

        assertTrue(result);
        verify(cartRepo, times(1)).delete(cart);
    }

    @Test
    public void testDeleteCartNotFound() {
        when(cartRepo.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cartService.deleteCart(1);
        });

        assertEquals("No cart found with id 1", exception.getMessage());
    }
}
