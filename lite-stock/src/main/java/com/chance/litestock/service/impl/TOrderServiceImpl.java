package com.chance.litestock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chance.litestock.domain.dao.TOrder;
import com.chance.litestock.service.TOrderService;
import com.chance.litestock.mapper.TOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 32166
* @description 针对表【t_order(订单表)】的数据库操作Service实现
* @createDate 2026-05-13 20:37:04
*/
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder>
    implements TOrderService{

}




