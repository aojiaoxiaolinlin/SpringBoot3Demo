package com.lin.controller;

import com.lin.api.HttpResult;
import com.lin.common.StatusEnum;
import com.lin.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("data")
public class DataController {

    @GetMapping("status")
    public HttpResult<List<Order>> getStatus() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("1", StatusEnum.WAITING));
        orders.add(new Order("2", StatusEnum.PROCESSING));
        orders.add(new Order("3", StatusEnum.FINISHED));
        return HttpResult.success(orders);
    }
}
