package com.lin.configuration.service;


import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author 霖霖
 * @Date 2025/2/13 09:55
 * @Description 自定义的UserDetailsService
 */
@Service
public class DbUserDetailsService implements UserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return User.withUsername("user")
                       .password("123456")
                       .passwordEncoder(passwordEncoder::encode)
                       .build();
        }
        throw new RuntimeException("登录失败");
    }
}
