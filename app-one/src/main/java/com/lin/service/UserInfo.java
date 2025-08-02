package com.lin.service;


import com.lin.entities.GithubUserInfo;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserInfo {

    @GetExchange("users")
    List<GithubUserInfo> getUsers();
}
