package buaa.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId
    private Long uid;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Integer gender;
    private String phone;
    private String email;
    private String nickname;
    private String avatar;
    private String role;
    @TableLogic
    private Integer isDeleted;
}
