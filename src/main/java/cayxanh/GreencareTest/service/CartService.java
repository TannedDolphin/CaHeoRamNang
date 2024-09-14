package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.CreateCartRequest;
import cayxanh.GreencareTest.dto.request.CreateOrderItemRequest;
import cayxanh.GreencareTest.entity.Cart;
import cayxanh.GreencareTest.entity.OrderItem;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.repo.CartRepo;
import cayxanh.GreencareTest.repo.OrderItemRepo;
import cayxanh.GreencareTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    public Cart createCart(CreateCartRequest request) {
        User user = userRepo.findById(request.getUserid())
                .orElseThrow(() -> new RuntimeException("User không tồn tại với ID: " + request.getUserid()));

        Cart cart = new Cart();
        cart.setUser(user);

        List<OrderItem> orderItems = request.getOrderItems().stream().map(orderItemRequest -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(orderItemRequest.getName());
            orderItem.setPrice(orderItemRequest.getPrice());
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setSubTotal(orderItemRequest.getSubTotal());

            // Đặt tham chiếu đến cart
            //cart.CreateOrderItem(orderItem);

            return orderItem;
        }).collect(Collectors.toList());

        cart.setOrderItems(orderItems);
        return cartRepo.save(cart);
    }

    public Cart updateCart(Integer id, CreateCartRequest request) {
        Cart cart = cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart không tồn tại với ID: " + id));

        User user = userRepo.findById(request.getUserid())
                .orElseThrow(() -> new RuntimeException("User không tồn tại với ID: " + request.getUserid()));

        cart.setUser(user);

        List<OrderItem> orderItems = request.getOrderItems().stream().map(orderItemRequest -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(orderItemRequest.getName());
            orderItem.setPrice(orderItemRequest.getPrice());
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setSubTotal(orderItemRequest.getSubTotal());

            // Đặt tham chiếu đến cart
            //cart.addOrderItem(orderItem);

            return orderItem;
        }).collect(Collectors.toList());

        cart.setOrderItems(orderItems);
        return cartRepo.save(cart);
    }

    public void deleteCart(Integer id) {
        if (!cartRepo.existsById(id)) {
            throw new RuntimeException("Cart không tồn tại với ID: " + id);
        }
        cartRepo.deleteById(id);
    }

    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

    public Cart getCartById(Integer id) {
        return cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart không tồn tại với ID: " + id));
    }
}
