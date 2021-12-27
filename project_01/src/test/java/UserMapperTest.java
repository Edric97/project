import com.edric.dao.UserMapper;
import com.edric.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper userMapper = context.getBean("userMapper", UserMapper.class);

    @Test
    public void queryUserByUsername() {
        System.out.println(userMapper.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userMapper.queryUserByUsernameAndPassword("admin", "admin"));
    }

    @Test
    public void saveUser() {
        System.out.println(userMapper.saveUser(new User(null, "admin3", "admin3", "admin3@qq.com")));
    }

}
