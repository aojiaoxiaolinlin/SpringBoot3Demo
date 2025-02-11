package com.one.service;


import com.one.entities.GithubUserInfo;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserInfo {

    @GetExchange("users")
    List<GithubUserInfo> getUsers();
}
