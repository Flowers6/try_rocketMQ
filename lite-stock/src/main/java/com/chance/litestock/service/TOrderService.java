package com.chance.litestock.service;

import com.chance.litestock.domain.dao.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chance.litestock.domain.dto.CreateOrder;

/**
* @author 32166
* @description 针对表【t_order(订单表)】的数据库操作Service
* @createDate 2026-05-13 20:37:04
*/
public interface TOrderService extends IService<TOrder> {

    void createOrder(CreateOrder createOrder);

    void pay(Long orderId);
}
