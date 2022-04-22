package buaa;

import buaa.mapper.CompanyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CompanyMapperTest {

    @Resource
    private CompanyMapper companyMapper;
    @Test
    public void deleteByIds() {
        List<Integer> idList =new ArrayList<>();
        idList.add(15);
        idList.add(29);
        idList.add(43);
        idList.add(57);
        idList.add(71);
        idList.add(85);
        idList.add(99);
        idList.add(113);
        companyMapper.deleteBatchIds(idList);
    }
}
