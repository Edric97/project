import com.edric.dao.BookMapper;
import com.edric.pojo.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class BookMapperTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookMapper bookMapper = context.getBean("bookMapper", BookMapper.class);

    @Test
    public void addBook() {
        System.out.println(bookMapper.addBook(new Book(null, "活着", "余华", new BigDecimal(32), 10000, 2000, null)));
    }

    @Test
    public void deleteBookById() {
        System.out.println(bookMapper.deleteBookById(29));
    }

    @Test
    public void updateBook() {
        System.out.println(bookMapper.updateBook(new Book(30, "在细雨中呼喊", "余华", new BigDecimal(36), 8002, 1680, null)));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookMapper.queryBookById(30));
    }
    
    @Test
    public void queryBooks() {
        List<Book> list = bookMapper.queryBooks();

        for (Book book : list) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookMapper.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookMapper.queryForPageItems(8, 4)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookMapper.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookMapper.queryForPageItemsByPrice(0, 4, 10, 50)) {
            System.out.println(book);
        }
    }

}
