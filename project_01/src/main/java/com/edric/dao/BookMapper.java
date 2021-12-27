package com.edric.dao;

import com.edric.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    int addBook(Book book);

    int deleteBookById(@Param("id") Integer id);

    int updateBook(Book book);

    Book queryBookById(@Param("id") Integer id);

    List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize);

    Integer queryForPageTotalCountByPrice(@Param("min") Integer min, @Param("max") Integer max);

    List<Book> queryForPageItemsByPrice(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize, @Param("min") Integer min, @Param("max") Integer max);

    void updateBookSalesAndStockByBookIdAndQuantity(@Param("bookId") Integer bookId, @Param("quantity") Integer quantity);
}
