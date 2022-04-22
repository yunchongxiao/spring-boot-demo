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
public class Company {
    @TableId
    private Integer cid;
    private String company;
    private String uscc;
    private String address;
    private String representative;
    private String phone;
    private String email;
    private String createTime;
    private String updateTime;
    @TableLogic
    private Integer deleted;
}
