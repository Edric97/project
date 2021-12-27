import com.edric.pojo.User;
import com.edric.service.UserService;
import com.edric.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserServiceImpl userService = context.getBean("userServiceImpl", UserServiceImpl.class);

    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "admin4", "admin4", "admin4@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "admin", "admin", "admin@qq.com")));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("admin"));
    }

}
