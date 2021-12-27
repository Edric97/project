import com.edric.pojo.Book;
import com.edric.service.impl.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookServiceImpl bookService = context.getBean("bookServiceImpl", BookServiceImpl.class);

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "许三观卖血记", "余华", new BigDecimal(38.2), 9356, 862, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(27);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(32, "兄弟", "余华", new BigDecimal(65), 6682, 968, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(32));
    }

    @Test
    public void queryBooks() {
        List<Book> list = bookService.queryBooks();

        for (Book book : list) {
            System.out.println(book);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, 4));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(0, 50, 1, 4));
    }
}
