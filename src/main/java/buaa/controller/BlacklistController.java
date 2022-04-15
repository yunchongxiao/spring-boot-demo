package buaa.controller;

import buaa.entity.Blacklist;
import buaa.service.BlacklistService;
import buaa.util.JWTUtil;
import buaa.util.Result;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/pre-loan")
public class BlacklistController {

    @Resource(name = "blacklistServiceImpl")
    private BlacklistService blacklistService;

    /**
     * 查询企业失信数据
     *
//     * @param token
     * @return
     */
    @GetMapping("/blacklist")
    public Result<List<Blacklist>> getBlacklist(@RequestHeader("X-Token") String token) {
        Claims claims = JWTUtil.parseToken(token, "BUAA");
        Long uid = Long.parseLong(claims.getSubject());
        System.out.println("请求网页的用户 uid = " + uid);
        Map<String, String> data = new HashMap<>();
        List<Blacklist> list = blacklistService.list();
        list.forEach(System.out::println);
        return Result.success(list);
    }

    @PostMapping("/deletelist")
    public Result<String> getBlacklist(@RequestHeader("X-Token") String token, @RequestBody Blacklist blacklist) {
        Claims claims = JWTUtil.parseToken(token, "BUAA");
        Long uid = Long.parseLong(claims.getSubject());
        System.out.println("请求网页的用户 uid = " + uid);
        System.out.println("请求删除的黑名单 bid = " + blacklist);
        blacklistService.removeById(blacklist);
        return Result.success("");
    }


}
