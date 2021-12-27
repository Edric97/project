package com.edric.service.impl;

import com.edric.dao.OrderMapper;
import com.edric.pojo.CartItem;
import com.edric.pojo.Order;
import com.edric.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void createOrder(Order order) {
        orderMapper.createOrder(order);
    }

    @Override
    public Integer getLastedOrderId() {
        return orderMapper.getLastedOrderId();
    }

    @Override
    public void addOrderItem(List<CartItem> cartOfUserId, Integer orderId) {
        orderMapper.addOrderItem(cartOfUserId, orderId);
    }

    @Override
    public List<Order> getMyOrder(Integer userId) {
        return orderMapper.getMyOrder(userId);
    }
}
