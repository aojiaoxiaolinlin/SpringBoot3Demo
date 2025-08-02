package com.lin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.entities.UserMoney;
import com.lin.mapper.UserMoneyMapper;
import com.lin.service.UserMoneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户余额表 服务实现类
 * </p>
 *
 * @author 霖霖
 * @since 2025-06-29
 */
@Service
public class UserMoneyServiceImpl extends ServiceImpl<UserMoneyMapper, UserMoney> implements UserMoneyService {


    @Override
    @Transactional
    public Boolean transfer(Integer from, Integer to, Long money) {
        UpdateWrapper<UserMoney> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", from).setSql("money = money - " + money);
        return this.update(updateWrapper) && this.doTransferUpdateMoney(to, "money = money + " + money);
    }

    public Boolean doTransferUpdateMoney(Integer id, String sql) {
        UpdateWrapper<UserMoney> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).setSql(sql);
        int i = 1 / 0;
        return this.update(updateWrapper);
    }
}
