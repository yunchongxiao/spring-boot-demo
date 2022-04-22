package buaa.service.impl;

import buaa.entity.Company;
import buaa.util.JWTUtil;
import buaa.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import buaa.mapper.UserMapper;
import buaa.entity.User;
import buaa.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    /**
     * 根据用户名返回用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User getByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public Boolean createUser(User user) {
        System.out.println("user = " + user);
        // 用户密码加密
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        // 返回存储状态
        return userMapper.insert(user) == 1;
    }

    @Override
    public Map<String, String> generateTokenMap(User user) {
        String token = JWTUtil.generateToken(user.getUid(), user.getUsername(), "BUAA", 7 * 24 * 60);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        // 返回成功提示，并附带 token
        return data;
    }

    public List<Map<String, Object>> getLike(String key) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("realname", key);
        List<User> companyList = userMapper.selectList(queryWrapper);
        List<Map<String, Object>> data = new ArrayList<>();
        for (User user : companyList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("value", user.getRealname());
            map.put("uid", user.getUid());
            data.add(map);
        }
        return data;
    }

}
