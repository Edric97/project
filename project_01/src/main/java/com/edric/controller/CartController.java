package com.edric.controller;

import com.edric.pojo.Book;
import com.edric.pojo.CartItem;
import com.edric.service.BookService;
import com.edric.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;
    @Autowired
    @Qualifier("cartServiceImpl")
    private CartService cartService;

    @RequestMapping("/exhibit")
    public String exhibit(HttpSession httpSession, Model model) {
        Integer userId = (Integer) httpSession.getAttribute("userId");

        if (userId == null) {
            return "user/login";
        }

        List<CartItem> cart = cartService.cart(userId);
        Integer totalQuantity = cartService.totalQuantity(userId);
        BigDecimal totalPrice = cartService.totalPrice(userId);

        model.addAttribute("cart", cart);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("totalPrice", totalPrice);

        return "cart/cart";
    }

    @RequestMapping("/add")
    public String add(HttpSession httpSession, Integer bookId, HttpServletRequest req, HttpServletResponse resp, Model model) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        int quantity = cartService.queryCertainBook(userId, bookId);
        Book book = bookService.queryBookById(bookId);
        CartItem cartItem = new CartItem(null, userId, bookId, book.getName(), 1, book.getPrice());

        if (quantity == Integer.MIN_VALUE) {
            //说明在数据库中，该用户并没有添加该书
            cartService.addBook(cartItem);
//            resp.sendRedirect(req.getHeader("Referer"));
            return "redirect:" + req.getHeader("Referer");
        } else {
            cartService.updateBook(userId, quantity + 1, bookId);
//            resp.sendRedirect(req.getHeader("Referer"));
            return "redirect:" + req.getHeader("Referer");
        }
    }

    @RequestMapping("/delete")
    public String delete(HttpSession httpSession, Integer bookId) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        cartService.deleteBook(userId, bookId);
        return "redirect:/cart/exhibit";
    }

    @RequestMapping("/clearAll")
    public String clearAll(HttpSession httpSession) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        cartService.clearAll(userId);
        return "redirect:/cart/exhibit";
    }

}
