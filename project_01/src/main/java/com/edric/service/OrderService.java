package com.edric.service;

import com.edric.pojo.CartItem;
import com.edric.pojo.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);

    Integer getLastedOrderId();

    void addOrderItem(List<CartItem> cartOfUserId, Integer orderId);

    List<Order> getMyOrder(Integer userId);

}
