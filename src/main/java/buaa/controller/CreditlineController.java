package buaa.controller;

import buaa.entity.Creditline;
import buaa.service.CreditlineService;
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
@RequestMapping("/pre-loan/creditline")
public class CreditlineController {

    @Resource(name = "creditlineServiceImpl")
    private CreditlineService creditlineService;

    /**
     * 查询企业失信数据
     * <p>
     * //     * @param token
     *
     * @return
     */
    @GetMapping("/getlist")
    public Result<Map<String, Object>> getCreditlineList(@RequestHeader("X-Token") String token,
                                                         @RequestParam Map<String, String> listQuery) {
        Claims claims = JWTUtil.parseToken(token, "BUAA");
        Long uid = Long.parseLong(claims.getSubject());
        System.out.println("请求网页的用户 uid = " + uid);
        System.out.println("listQuery = " + listQuery);
        Long current = Long.parseLong(listQuery.get("page"));
        Long size = Long.parseLong(listQuery.get("limit"));
        System.out.println("current = " + current);
        System.out.println("size = " + size);
        // 创建分页器
        Page<Creditline> page = new Page<>(current, size);
        // 根据分页器查询数据
        Page<Creditline> creditlinePage = creditlineService.page(page);
        // 创建返回数据所需Map
        Map<String, Object> data = new HashMap<>();
        System.out.println("creditlinePage.getTotal() = " + creditlinePage.getTotal());
        data.put("total", creditlinePage.getTotal());
        data.put("items", creditlinePage.getRecords());
        return Result.success(data);
    }
}
