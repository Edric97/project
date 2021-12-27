package com.edric.controller;

import com.edric.pojo.Book;
import com.edric.pojo.Page;
import com.edric.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/client")
public class ClientBookController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/page")
    public String page(Integer pageNo, Model model) {
        Page<Book> page = bookService.page(pageNo, 4);
        page.setUrl("/client/page?pageNo=");
        model.addAttribute("page", page);
        return "client/index";
    }

    @RequestMapping("/pageByPrice")
    public String pageByPrice(Integer min, Integer max, Integer pageNo, Model model) {
        if (min < 0) {
            min = 0;
        }
        if (max < 0) {
            max = min + 1;
        }

        StringBuilder sb = new StringBuilder("/client/pageByPrice?");
        if (min != null) {
            sb.append("min=").append(min).append("&");
        }
        if (max != Integer.MAX_VALUE) {
            sb.append("max=").append(max).append("&pageNo=");
        }

        Page<Book> page = bookService.pageByPrice(min, max, pageNo, 4);
        page.setUrl(sb.toString());
        model.addAttribute("page", page);
        return "client/index";
    }

}
