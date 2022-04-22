package buaa.controller;

import buaa.service.CompanyService;
import buaa.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/company")
public class CompanyController {

    @Resource(name = "companyServiceImpl")
    private CompanyService companyService;

    /**
     * 查询企业失信数据
     *
     * @return
     */
    @GetMapping("/query")
    public Result<Object> getCompany(@RequestParam Map<String, String> listQuery) {
        System.out.println("listQuery = " + listQuery);
        List<Map<String, Object>> companyList = companyService.getLike(listQuery.get("company"));
        return Result.success(companyList);
    }
    /**
     * 查询企业失信数据
     *
     * @return
     */
    @GetMapping("/{uscc}")
    public Result<Object> getCompany(@PathVariable String uscc) {
        System.out.println("uscc = " + uscc);

        return Result.success(companyService.getCompanyDetails(uscc));
    }
}
