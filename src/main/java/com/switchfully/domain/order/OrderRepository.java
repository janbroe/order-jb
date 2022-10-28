package com.switchfully.domain.order;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {

    private final Map<String, Order> orderRepository;

    public OrderRepository(Map<String, Order> orderRepository) {
        this.orderRepository = new HashMap<>();
    }

    public void addOrder(Order order) {
        orderRepository.put(order.getId(), order);
    }
}
