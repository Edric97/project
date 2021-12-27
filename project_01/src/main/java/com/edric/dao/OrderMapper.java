package com.edric.dao;

import com.edric.pojo.CartItem;
import com.edric.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    void createOrder(Order order);

    Integer getLastedOrderId();

    void addOrderItem(@Param("cartOfUserId") List<CartItem> cartOfUserId, @Param("orderId") Integer orderId);

    List<Order> getMyOrder(@Param("userId") Integer userId);

}
