package buaa.service.impl;

import buaa.entity.Blacklist;
import buaa.mapper.BlacklistMapper;
import buaa.service.BlacklistService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements BlacklistService {

    @Resource
    private BlacklistMapper blacklistMapper;

    @Override
    public Page<Blacklist> pageByListQuery(Map<String, String> listQuery) {
        System.out.println("listQuery = " + listQuery);
        String company = listQuery.get("company");
        String representative = listQuery.get("representative");
        String phone = listQuery.get("phone");
        String email = listQuery.get("email");
        String address = listQuery.get("address");
        Page<Blacklist> page = new Page<>(
                Long.parseLong(listQuery.get("current")),
                Long.parseLong(listQuery.get("size"))
        );
        QueryWrapper<Blacklist> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(company), "company", company);
        queryWrapper.like(StringUtils.isNotBlank(representative), "representative", representative);
        queryWrapper.like(StringUtils.isNotBlank(phone), "phone", phone);
        queryWrapper.like(StringUtils.isNotBlank(email), "email", email);
        queryWrapper.like(StringUtils.isNotBlank(address), "address", address);
        return blacklistMapper.selectPage(page, queryWrapper);
    }
}
