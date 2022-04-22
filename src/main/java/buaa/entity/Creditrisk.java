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
public class Creditrisk {
    @TableId
    private Integer crid;
    private String company; // 公司名称
    private String model; // 模型名
    private Double risk;  // 风险
    private Date updateTime; // 结果更新时间
    private String status;
    private Long duration;
    @TableLogic
    private Integer deleted;
}
