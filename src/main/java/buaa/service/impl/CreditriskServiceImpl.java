package buaa.service.impl;

import buaa.entity.Creditrisk;
import buaa.mapper.CreditriskMapper;
import buaa.service.CreditriskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CreditriskServiceImpl extends ServiceImpl<CreditriskMapper, Creditrisk> implements CreditriskService {

    @Resource
    private CreditriskMapper creditriskMapper;
    @Override
    public Page<Creditrisk> pageByListQuery(Map<String, String> listQuery) {
        System.out.println("listQuery = " + listQuery);
        String company = listQuery.get("company");
        String model = listQuery.get("model");
        String status = listQuery.get("status");
        Page<Creditrisk> page = new Page<>(
                Long.parseLong(listQuery.get("current")),
                Long.parseLong(listQuery.get("size"))
        );
        QueryWrapper<Creditrisk> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(company), "company", company);
        queryWrapper.eq(StringUtils.isNotBlank(model), "model", model);
        queryWrapper.eq(StringUtils.isNotBlank(status), "status", status);
        return creditriskMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<String> getDistinctByCol(String col) {
        QueryWrapper<Creditrisk> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(col);
        return creditriskMapper.selectObjs(queryWrapper)
                .stream()
                .distinct()
                .map(o -> (String)o)
                .collect(Collectors.toList());
    }

}
