package buaa.service;

import buaa.entity.Creditline;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


public interface CreditlineService extends IService<Creditline> {


    Page<Creditline> pageByListQuery(Map<String, String> listQuery);
}
