package buaa;

import buaa.entity.User;
import buaa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ServiceTest {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Test
    public void testGetCount() {
        long count = userService.count();
        System.out.println("count = " + count);
    }

    @Test
    public void testGetById() {
        User user = userService.getById(10L);
        System.out.println("user = " + user);
    }

    @Test
    public void testGetByUsername() {
        System.out.println("userService.getByUsername(\"admin\") = " + userService.getByUsername("admin"));
        System.out.println("userService.getByUsername(\"yunchongxiao\") = " + userService.getByUsername("yunchongxiao"));
    }
}
