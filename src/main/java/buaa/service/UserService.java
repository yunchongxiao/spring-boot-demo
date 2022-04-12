package buaa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import buaa.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {
    User getByUsername(String username);

    Boolean createUser(User user);

    Map<String, String> generateTokenMap(User user);
}
