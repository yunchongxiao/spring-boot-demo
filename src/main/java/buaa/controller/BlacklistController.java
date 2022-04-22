package buaa.controller;

import buaa.entity.Blacklist;
import buaa.service.BlacklistService;
import buaa.util.JWTUtil;
import buaa.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/pre-loan/blacklist")
public class BlacklistController {

    @Resource(name = "blacklistServiceImpl")
    private BlacklistService blacklistService;

    /**
     * 查询企业失信数据
     *
     * @return
     */
    @GetMapping("/getlist")
    public Result<Map<String, Object>> getBlacklist(@RequestParam Map<String, String> listQuery) {
        Page<Blacklist> blacklistPage = blacklistService.pageByListQuery(listQuery);
        Map<String, Object> data = new HashMap<>();
        data.put("total", blacklistPage.getTotal());
        data.put("items", blacklistPage.getRecords());
        return Result.success("", data);
    }

    @PostMapping("/delete")
    public Result<String> getBlacklist(@RequestHeader("X-Token") String token, @RequestBody Blacklist blacklist) {
        Claims claims = JWTUtil.parseToken(token, "BUAA");
        Long uid = Long.parseLong(claims.getSubject());
        System.out.println("请求网页的用户 uid = " + uid);
        System.out.println("请求删除的黑名单 = " + blacklist);
        boolean result = blacklistService.removeById(blacklist);
        return result ? Result.success("删除黑名单成功"): Result.failed("删除黑名单失败");
    }
    @PostMapping("/add")
    public Result<String> addBlacklist(@RequestBody Blacklist blacklist) {
        System.out.println("请求添加的黑名单 = " + blacklist);
        boolean save = blacklistService.save(blacklist);
        if (save) {
            return Result.success("添加黑名单成功");
        }
        else {
            return Result.failed("添加黑名单失败");
        }
    }


}
