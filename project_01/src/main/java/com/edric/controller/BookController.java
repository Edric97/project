package com.edric.controller;

import com.edric.pojo.Book;
import com.edric.pojo.Page;
import com.edric.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/book/manager")
public class BookController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/getBook")
    public String getBook(int id, Model model) {
        Book book = bookService.queryBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("id", id);
        return "manager/book_edit";
    }

    @RequestMapping("/add")
    public String add(String name, BigDecimal price, String author, Integer sales, Integer stock, Integer pageNo) {
        pageNo += 1;
        bookService.addBook(new Book(null, name, author, price, sales, stock, null));
        return "redirect:/book/manager/page?pageNo=" + pageNo;
    }

    @RequestMapping("/delete")
    public String delete(int id, Integer pageNo) {
        bookService.deleteBookById(id);
        return "redirect:/book/manager/page?pageNo=" + pageNo;
    }

    @RequestMapping("/update")
    public String update(Integer id, String name, BigDecimal price, String author, Integer sales, Integer stock, Integer pageNo) {
        bookService.updateBook(new Book(id, name, author, price, sales, stock, null));
        return "redirect:/book/manager/page?pageNo=" + pageNo;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Book> books = bookService.queryBooks();
        model.addAttribute("books", books);
        return "manager/book_manager";
    }

    /**
     * 处理分页功能
     * @return
     */
    @RequestMapping("/page")
    public String page(Integer pageNo, Model model) {
        Page<Book> page = bookService.page(pageNo, 4);
        page.setUrl("/book/manager/page?pageNo=");
        model.addAttribute("page", page);
        return "manager/book_manager";
    }

}
