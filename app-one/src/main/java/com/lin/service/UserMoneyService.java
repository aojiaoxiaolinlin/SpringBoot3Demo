package com.lin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.entities.UserMoney;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户余额表 服务类
 * </p>
 *
 * @author 霖霖
 * @since 2025-06-29
 */
@Service
public interface UserMoneyService extends IService<UserMoney> {

    Boolean transfer(Integer from, Integer to, Long money);
}
