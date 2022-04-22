package buaa.service.impl;

import buaa.entity.Blacklist;
import buaa.entity.Company;
import buaa.mapper.BlacklistMapper;
import buaa.mapper.CompanyMapper;
import buaa.service.CompanyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private BlacklistMapper blacklistMapper;

    public List<Map<String, Object>> getLike(String key) {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("company", key);
        List<Company> companyList = companyMapper.selectList(queryWrapper);
        List<Map<String, Object>> data = new ArrayList<>();
        for (Company company : companyList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("value", company.getCompany());
            map.put("cid", company.getCid());
            map.put("uscc", company.getUscc());
            data.add(map);
        }
        return data;
    }

    @Override
    public Map<String, Object> getCompanyDetails(String uscc) {
        QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
        companyQueryWrapper.eq("uscc", uscc);
        Company company = companyMapper.selectOne(companyQueryWrapper);
        QueryWrapper<Blacklist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uscc", company.getUscc());
        Blacklist blacklist = blacklistMapper.selectOne(queryWrapper);
        Map<String, Object> data = new HashMap<>();
        if (Objects.nonNull(blacklist)) {
            data.put("bid", blacklist.getBid());
            data.put("isBreak", true);
            data.put("reason", blacklist.getReason());
        }
        else {
            data.put("bid", null);
            data.put("isBreak", false);
            data.put("reason", "");
        }
        data.put("cid", company.getCid());
        data.put("uscc", company.getUscc());
        data.put("company", company.getCompany());
        data.put("representative", company.getRepresentative());
        data.put("phone", company.getPhone());
        data.put("email", company.getEmail());
        data.put("address", company.getAddress());
        data.put("realname", "肖云冲");
        data.put("introduction", "数据库不尚未设计该字段");
        System.out.println("data = " + data);
        return data;
    }
}
