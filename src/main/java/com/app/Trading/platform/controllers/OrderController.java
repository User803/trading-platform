package com.app.Trading.platform.controllers;

import com.app.Trading.platform.domain.OrderType;
import com.app.Trading.platform.model.Coin;
import com.app.Trading.platform.model.Order;
import com.app.Trading.platform.model.User;
import com.app.Trading.platform.request.CreateOrderRequest;
import com.app.Trading.platform.services.CoinService;
import com.app.Trading.platform.services.OrderService;
import com.app.Trading.platform.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CoinService coinService;
//    private final WalletTransactionService walletTransactionService;

    public OrderController(OrderService orderService, UserService userService, CoinService coinService) {
        this.orderService = orderService;
        this.userService = userService;
        this.coinService = coinService;
    }

    @PostMapping("/pay")
    public ResponseEntity<Order> payOrderPayment(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CreateOrderRequest createOrderRequest) {

        User user = userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(createOrderRequest.getCoinId());

        Order order = orderService.processOrder(
                coin,
                createOrderRequest.getQuantity(),
                createOrderRequest.getOrderType(),
                user
        );

        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId) {

        if (jwt == null) {
            throw new RuntimeException("JWT Token missing");
        }
        User user = userService.findUserProfileByJwt(jwt);

        Order order = orderService.getOrderById(orderId);
        if (order.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(order);
        }

        throw new RuntimeException("Access forbidden");
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersForUser(
            @RequestHeader("Authorization") String jwt,
            @RequestParam(required = false) OrderType orderType,
            @RequestParam(required = false) String assetSymbol) {

        if (jwt == null) {
            throw new RuntimeException("JWT Token missing");
        }
        Long userId = userService.findUserProfileByJwt(jwt).getId();

        List<Order> order = orderService.getAllOrdersOfUser(userId, orderType, assetSymbol);

        return ResponseEntity.ok(order);
    }
}
