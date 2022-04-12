package buaa.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderUtil extends BCryptPasswordEncoder {

    @Override
    // 密码加密
    public String encode(CharSequence rawPassword) {
        return super.encode(rawPassword);
    }

    @Override
    // 密码对比
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        return super.matches(rawPassword, encodedPassword);
    }

}
