package com.edric.service.impl;

import com.edric.dao.CartMapper;
import com.edric.pojo.CartItem;
import com.edric.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @Override
    public List<CartItem> cart(Integer userId) {
        return cartMapper.cart(userId);
    }

    @Override
    public int queryCertainBook(Integer userId, Integer bookId) {
        Integer integer = cartMapper.queryCertainBook(userId, bookId);
        if (integer == null) {
            return Integer.MIN_VALUE;
        }
        return integer;
    }

    @Override
    public void addBook(CartItem cartItem) {
        cartMapper.addBook(cartItem);
    }

    @Override
    public void deleteBook(Integer userId, Integer bookId) {
        cartMapper.deleteBook(userId, bookId);
    }

    @Override
    public void deleteBookByUserId(Integer userId) {
        cartMapper.deleteBookByUserId(userId);
    }

    @Override
    public void updateBook(Integer userId, Integer quantity, Integer bookId) {
        cartMapper.updateBook(userId, quantity, bookId);
    }

    @Override
    public int totalQuantity(Integer userId) {
        Integer integer = cartMapper.totalQuantity(userId);
        if (integer == null) {
            return 0;
        }
        return integer;
    }

    @Override
    public BigDecimal totalPrice(Integer userId) {
        BigDecimal bigDecimal = cartMapper.totalPrice(userId);
        if (bigDecimal == null) {
            return new BigDecimal(0);
        }
        return bigDecimal;
    }

    @Override
    public void clearAll(Integer userId) {
        cartMapper.clearAll(userId);
    }

    @Override
    public List<Integer> queryBookIdByUserId(Integer userId) {
        return cartMapper.queryBookIdByUserId(userId);
    }

}
