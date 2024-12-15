package com.shop.application.dao;

import com.shop.application.domain.order.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderDAO {
    private Map<Integer, Order> orders = new HashMap<Integer, Order>();

    public void save(Order order){
        orders.put(order.getOrdernumber(), order);
    }
    public Order find(int orderId){
        return orders.get(orderId);
    }
}
