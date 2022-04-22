package buaa.service;

import buaa.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;


public interface CompanyService extends IService<Company> {

    List<Map<String, Object>> getLike(String key);

    Map<String, Object> getCompanyDetails(String uscc);
}
