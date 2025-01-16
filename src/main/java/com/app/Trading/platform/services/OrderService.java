package com.app.Trading.platform.services;

import com.app.Trading.platform.domain.OrderType;
import com.app.Trading.platform.model.Coin;
import com.app.Trading.platform.model.Order;
import com.app.Trading.platform.model.OrderItem;
import com.app.Trading.platform.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, OrderItem orderItem, OrderType orderType);
    Order getOrderById(Long orderId);
    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbol);
    Order processOrder(Coin coin, double quantity, OrderType orderType, User user);

}
