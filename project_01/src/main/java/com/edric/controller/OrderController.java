package com.edric.controller;

import com.edric.pojo.CartItem;
import com.edric.pojo.Order;
import com.edric.service.BookService;
import com.edric.service.CartService;
import com.edric.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/createOrder")
    public String createOrder(Integer userId, Model model) {
        List<CartItem> cartOfUserId = cartService.cart(userId);//当前用户的购物车中的所有内容
        BigDecimal totalPrice = cartService.totalPrice(userId);//当前用户购物车中的总金额

        Order order = new Order(null, userId, null, totalPrice, new Timestamp(System.currentTimeMillis()));

        orderService.createOrder(order);
        Integer orderId = orderService.getLastedOrderId();
        orderService.addOrderItem(cartOfUserId, orderId);


        cartService.deleteBookByUserId(userId);//在数据库t_cart表中：把当前用户的的所有图书信息全部删除
        for (CartItem cartItem : cartOfUserId) {

            bookService.updateBookSalesAndStockByBookIdAndQuantity(cartItem.getBookId(), cartItem.getQuantity());
            //在数据库t_book表中：更新图书的销量，库存信息

        }

        orderId = (int) System.currentTimeMillis();


        model.addAttribute("orderId", orderId);

        return "cart/checkout";
    }

    @RequestMapping("/showMyOrder")
    public String showMyOrder(Integer userId, Model model) {
        List<Order> myOrder = orderService.getMyOrder(userId);
        model.addAttribute("myOrder", myOrder);
        return "order/order";
    }

}
