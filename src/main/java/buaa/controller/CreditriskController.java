package buaa.controller;

import buaa.entity.Creditrisk;
import buaa.service.CreditriskService;
import buaa.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/pre-loan/creditrisk")
public class CreditriskController {

    @Resource
    private CreditriskService creditriskService;

    @GetMapping("/getlist")
    public Result<Object> getCreditriskList(@RequestParam Map<String, String> query) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", creditriskService.pageByListQuery(query));
        data.put("allModel", creditriskService.getDistinctByCol("model"));
        data.put("allStatus", creditriskService.getDistinctByCol("status"));
        return Result.success("", data);
    }

    @PostMapping("/delete")
    public Result<Object> getCreditriskList(@RequestBody Creditrisk creditrisk) {
        boolean result = creditriskService.removeById(creditrisk);
        return result ? Result.success("风险评估记录删除成功") : Result.failed("风险评估记录删除失败");

    }
}
