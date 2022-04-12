package buaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import buaa.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
