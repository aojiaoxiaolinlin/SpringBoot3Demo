package com.lin.springboot3demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("getGraphQL")
public class ResourceController {
    @RequestMapping()
    public String getGraphQL() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("graphql/schema.graphqls")) {
            if (is != null) {
                return new String(is.readAllBytes());
            }
            return "null";
        }
    }
}
