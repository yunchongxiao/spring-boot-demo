package buaa.service;

import buaa.entity.Creditrisk;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface CreditriskService extends IService<Creditrisk> {
    Page<Creditrisk> pageByListQuery(Map<String, String> listQuery);

    List<String> getDistinctByCol(String col);
}
