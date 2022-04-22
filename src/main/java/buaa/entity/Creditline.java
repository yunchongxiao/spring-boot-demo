package buaa.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Creditline {
    @TableId
    private Integer cid;
    private String company; // 公司名称
    private String financeProduct; // 融资产品【信用贷款, ...】
    private String businessType;  // 业务类型 [额度申请, 额度变更, 额度解冻？]
    private Double applyLine; // 申请额度
    private Double creditLine;  // 授信额度
    private String status; // 额度状态
    private Date applyTime; // 申请时间
    private Date dueTime; // 到期时间
    private String model; // 模型名称
    @TableLogic
    private Integer deleted;
}
