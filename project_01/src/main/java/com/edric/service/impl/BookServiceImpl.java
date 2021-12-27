package com.edric.service.impl;

import com.edric.dao.BookMapper;
import com.edric.pojo.Book;
import com.edric.pojo.Page;
import com.edric.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookMapper.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookMapper.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {

        Page<Book> page = new Page<>();


        page.setPageSize(pageSize);// 设置当前页码

        page.setPageSize(pageSize);// 设置每页显示的数量

        Integer pageTotalCount = bookMapper.queryForPageTotalCount();// 求总记录数
        page.setPageTotalCount(pageTotalCount); // 设置总记录数

        Integer pageTotal = pageTotalCount / pageSize;// 求总页码
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);// 设置总页码

        page.setPageNo(pageNo);

        Integer begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookMapper.queryForPageItems(begin, pageSize);// 求当前页数据
        page.setItems(items);// 设置当前页数据

        return page;

    }

    @Override
    public Page<Book> pageByPrice(Integer min, Integer max, Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();


        page.setPageSize(pageSize);// 设置当前页码

        page.setPageSize(pageSize);// 设置每页显示的数量

        Integer pageTotalCount = bookMapper.queryForPageTotalCountByPrice(min, max);// 求总记录数
        page.setPageTotalCount(pageTotalCount); // 设置总记录数

        Integer pageTotal = pageTotalCount / pageSize;// 求总页码
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);// 设置总页码

        page.setPageNo(pageNo);

        Integer begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookMapper.queryForPageItemsByPrice(begin, pageSize, min, max);// 求当前页数据
        page.setItems(items);// 设置当前页数据

        return page;
    }
}
