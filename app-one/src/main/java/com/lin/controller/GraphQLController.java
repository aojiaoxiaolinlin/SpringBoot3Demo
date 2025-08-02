package com.lin.controller;

import com.lin.entities.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLController {
    @QueryMapping
    public User user(@Argument String id) {
        return new User(id, 20, "霖霖", "1589861957@qq.com");
    }
}
