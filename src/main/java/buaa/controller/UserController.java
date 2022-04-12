package buaa.controller;

import buaa.util.JWTUtil;
import buaa.util.Result;
import buaa.entity.User;
import buaa.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 注册用户，并对用户密码进行加密
     * 登录成功后应该设置 token
     *
     * @param user
     * @return
     */
    @PostMapping("/signup")
    public Result<Map<String, String>> signup(@RequestBody User user) {
        System.out.println("user = " + user);
        // 通过用户名验证用户是否已存在
        if (userService.getByUsername(user.getUsername()) != null) {
            return Result.failed("用户名已存在");
        } else {
            // 用户注册
            if (userService.createUser(user)) {
                Map<String, String> data = userService.generateTokenMap(user);
                return Result.success(data);
            } else {
                return Result.failed("服务器故障！请稍后重试或联系服务员。");
            }
        }
    }

    /**
     * 用户登录，验证通过后返回 token
     *
     * @param userForm
     * @return
     */
    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody User userForm) {
        // 通过用户名查询用户是否存在
        User user = userService.getByUsername(userForm.getUsername());
        if (user == null) {
            // 用户不存在，返回提示
            return Result.failed("用户不存在");
        }
        // 若用户存在，则继续进行密码验证
        else if (!new BCryptPasswordEncoder().matches(userForm.getPassword(), user.getPassword())) {
            // 密码不匹配，返回提示
            return Result.failed("密码错误");
        } else {
            // TODO 登录用户对应 token 的生成
            // TODO 用户身份存入 redis 缓存
            // 返回成功提示，并附带 token
            Map<String, String> data = userService.generateTokenMap(user);
            return Result.success(data);
        }
    }

    /**
     * 根据用户 token 查询用户对应信息，如头像、昵称等
     *
     * @param token
     * @return
     */
    @ResponseBody
    @GetMapping("/info")
    public Result<Map<String, String>> info(@RequestHeader("X-Token") String token) {
        Claims claims = JWTUtil.parseToken(token, "BUAA");
        Long uid = Long.parseLong(claims.getSubject());
        Map<String, String> data = new HashMap<>();
        // TODO 根据 token 在 redis 缓存中查询对应用户的信息
        User user = userService.getById(uid);
        data.put("name", user.getNickname());
        data.put("avatar", user.getAvatar());
        // 返回用户信息
        return Result.success(data);
    }


    @PostMapping("/logout")
    public Result logout(@RequestHeader("X-token") String token) {
        // TODO 根据 token 在 redis 中删除对应用户信息缓存
        return Result.success("已退出登录");
    }
}
