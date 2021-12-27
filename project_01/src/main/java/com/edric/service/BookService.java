package com.edric.service;

import com.edric.pojo.Book;
import com.edric.pojo.Page;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(Integer pageNo, Integer pageSize);

    Page<Book> pageByPrice(Integer min, Integer max, Integer pageNo, Integer pageSize);

    void updateBookSalesAndStockByBookIdAndQuantity(Integer bookId, Integer quantity);
}
