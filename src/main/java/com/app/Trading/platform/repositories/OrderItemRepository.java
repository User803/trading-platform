package com.app.Trading.platform.repositories;

import com.app.Trading.platform.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
