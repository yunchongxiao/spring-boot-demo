package buaa.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blacklist {
    @TableId
    private Integer bid;
    private String company;
    private String uscc;
    private String address;
    private String representative;
    private String reason;
    private String phone;
    private String email;
    private String breakTime;
    private String createTime;
    private String updateTime;
    @TableLogic
    private Integer deleted;
}
