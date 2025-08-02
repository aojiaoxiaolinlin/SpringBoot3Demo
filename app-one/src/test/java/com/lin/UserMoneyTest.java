package com.lin;

import com.lin.entities.UserMoney;
import com.lin.service.UserMoneyService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMoneyTest {

    @Resource
    private UserMoneyService userMoneyService;


    @Test
    void initData(){
        UserMoney userMoney = new UserMoney();
        userMoney.setUsername("霖霖");
        userMoney.setMoney(10000L);
        userMoneyService.save(userMoney);

        userMoney = new UserMoney();
        userMoney.setUsername("艺洁");
        userMoney.setMoney(10000L);
        userMoneyService.save(userMoney);
    }

    @Test
    void testTransfer(){
        userMoneyService.transfer(1, 2, 1000L);
    }
}
