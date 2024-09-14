package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.repo.CartRepo;
import cayxanh.GreencareTest.repo.OrderItemRepo;
import cayxanh.GreencareTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

}
