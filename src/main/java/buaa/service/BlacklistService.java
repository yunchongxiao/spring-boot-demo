package buaa.service;

import buaa.entity.Blacklist;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


public interface BlacklistService extends IService<Blacklist> {

    Page<Blacklist> pageByListQuery(Map<String, String> listQuery);
}
