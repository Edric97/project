package com.edric.dao;

import com.edric.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CartMapper {

    List<CartItem> cart(@Param("userId") Integer userId);

    Integer queryCertainBook(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    Integer addBook(CartItem cartItem);

    int deleteBook(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    int updateBook(@Param("userId") Integer userId, @Param("quantity") Integer quantity, @Param("bookId") Integer bookId);

    Integer totalQuantity(@Param("userId") Integer userId);

    BigDecimal totalPrice(@Param("userId") Integer userId);

    int clearAll(Integer userId);

    int deleteBookByUserId(@Param("userId") Integer userId);

    List<Integer> queryBookIdByUserId(@Param("userId") Integer userId);

}
