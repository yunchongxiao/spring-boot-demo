package buaa.serviceTest;

import buaa.service.CreditriskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CreditriskServiceTest {

    @Resource
    private CreditriskService creditriskService;
    @Test
    public void getAllStatusTest() {
        System.out.println(creditriskService.getDistinctByCol("status"));
        System.out.println(creditriskService.getDistinctByCol("model"));
    }

}
